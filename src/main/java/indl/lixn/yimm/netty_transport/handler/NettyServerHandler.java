package indl.lixn.yimm.netty_transport.handler;

import indl.lixn.yimm.netty_transport.NettyChannel;
import indl.lixn.yimm.transport.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lixn
 * @description
 * @date 2023/01/04 11:20
 **/
public class NettyServerHandler extends ChannelDuplexHandler {

    private final Map<String, Channel> channels = new ConcurrentHashMap<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 传播了事件应该在逻辑走完之后还是在这之前，有什么区别？
        ctx.fireChannelRead(msg);
        // 登记起来
        io.netty.channel.Channel channel = (io.netty.channel.Channel) msg;
        channels.put(channel.id().asShortText(), new NettyChannel(channel));
    }

    public Map<String, Channel> getChannelMap() {
        // Producer extends Consumer super
        return this.channels;
    }
}
