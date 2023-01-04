package indl.lixn.yimm.netty_transport;

import indl.lixn.yimm.serialization.Serialization;
import indl.lixn.yimm.serialization.impl.JsonSerialization;
import indl.lixn.yimm.transport.base.BaseChannel;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lixn
 * @description
 * @date 2023/01/04 10:00
 **/
public class NettyChannel extends BaseChannel {

    private static final Logger log = LoggerFactory.getLogger(NettyChannel.class);

    /*
        increase the reference count of bytebuf by 1
        buf.retain();
        decrease the reference count of bytebuf by 1
        buf.release();
     */

    private final Channel channel;

    private static final int MAX_RETRY = 3;

    public NettyChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String id() {
        return channel.id().asShortText();
    }

    @Override
    public boolean isConnected() {
        return channel.isOpen();
    }

    @Override
    public Serialization getSerialization() {
        return JsonSerialization.getInstance();
    }

    @Override
    public void close() {
        try {
            final ChannelFuture closeFuture = channel.close();
            // 这样写的好处是减少 future.isDone() 的判断吗？
            closeFuture.addListener((ChannelFutureListener)cf -> {
                int retryCount = 0;
                while (retryCount <= MAX_RETRY) {
                    if (!cf.isSuccess()) {
                        retryCount++;
                        channel.close();
                    } else {
                        break;
                    }
                }
                if (retryCount >= MAX_RETRY) {
                    log.error("NettyChannel关闭失败，已重试 {} 次", MAX_RETRY);
                } else {
                    log.info("NettyChannel关闭成功");
                }
            });
        } catch (Exception ex) {
            log.error("NettyChannel关闭异常：" + ex);
        }
    }

    @Override
    public boolean isClosed() {
        return !channel.isActive();
    }

    @Override
    public void send(byte[] data) {
        // MARK：这里可以不用 encode了，直接 codec.encode即可
        ByteBuf buf = Unpooled.copiedBuffer(data);
        final ChannelFuture sendFuture = channel.writeAndFlush(buf.retain().duplicate());
        sendFuture.addListener((ChannelFutureListener) future -> {
            int retryCount = 0;
            while (retryCount < MAX_RETRY) {
                if (!future.isSuccess()) {
                    channel.writeAndFlush(buf);
                    retryCount++;
                } else {
                    buf.release();
                    break;
                }
            }
            if (retryCount >= MAX_RETRY) {
                log.error("NettyChannel {} 发送 {} 失败，已重试 {} 次", id(), data, MAX_RETRY);
            } else {
                log.info("NettyChannel {} 发送 {} 成功", id(), data);
            }
        });
    }

}
