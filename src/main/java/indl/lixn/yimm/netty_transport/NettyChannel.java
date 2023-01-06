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

import java.net.InetSocketAddress;

import static indl.lixn.yimm.netty_transport.constant.CommonConstant.RETRY_TIME;

/**
 * @author lixn
 * @description
 * @date 2023/01/04 10:00
 **/
public class NettyChannel extends BaseChannel {

    private static final Logger log = LoggerFactory.getLogger(NettyChannel.class);

    private final Channel channel;

    public NettyChannel(Channel channel) {
        this.channel = channel;
    }

    public InetSocketAddress getRemoteAddress() {
        return (InetSocketAddress) this.channel.remoteAddress();
    }

    public InetSocketAddress getLocalAddress() {
        return (InetSocketAddress) this.channel.localAddress();
    }

    @Override
    public String id() {
        return channel.id().asShortText();
    }

    @Override
    public void send(byte[] data) {
        if (isClosed()) {
            log.error("NettyChannel {} >>> 处于关闭状态，无法发送消息", id());
            return;
        }
        if (!isConnected()) {
            log.error("NettyChannel {} >>> 尚未连接上，无法发送消息", id());
            return;
        }
        // MARK：这里可以不用 encode了，直接 codec.encode即可
        ByteBuf buf = Unpooled.copiedBuffer(data);
        final ChannelFuture sendFuture = channel.writeAndFlush(buf.retain().duplicate());
        sendFuture.addListener((ChannelFutureListener) future -> {
            int retryCount = 0;
            while (retryCount < RETRY_TIME) {
                if (!future.isSuccess()) {
                    channel.writeAndFlush(buf);
                    retryCount++;
                } else {
                    buf.release();
                    break;
                }
            }
            if (retryCount >= RETRY_TIME) {
                log.error("NettyChannel {} >>> 发送 {} 失败，已重试 {} 次", id(), data, RETRY_TIME);
            } else {
                log.info("NettyChannel {} >>> 发送 {} 成功", id(), data);
            }
        });
    }

    @Override
    public void close() {
        try {
            final ChannelFuture closeFuture = channel.close();
            // 这样写的好处是减少 future.isDone() 的判断吗？
            closeFuture.addListener((ChannelFutureListener)cf -> {
                int retryCount = 0;
                while (retryCount <= RETRY_TIME) {
                    if (!cf.isSuccess()) {
                        retryCount++;
                        channel.close();
                    } else {
                        break;
                    }
                }
                if (retryCount >= RETRY_TIME) {
                    log.error("NettyChannel >>> 关闭失败，已重试 {} 次", RETRY_TIME);
                } else {
                    log.info("NettyChannel >>> 关闭成功");
                }
            });
        } catch (Exception ex) {
            log.error("NettyChannel >>> 关闭异常：" + ex);
        }
    }

    @Override
    public boolean isClosed() {
        return !channel.isActive();
    }

    @Override
    public boolean isConnected() {
        return channel.isOpen();
    }

    @Override
    public Serialization getSerialization() {
        return JsonSerialization.getInstance();
    }

}
