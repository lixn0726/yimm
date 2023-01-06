package indl.lixn.yimm.netty_transport.handler;

import indl.lixn.yimm.netty_transport.NettyChannel;
import indl.lixn.yimm.transport.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lixn
 * @description
 * @date 2023/01/04 11:20
 **/
public class NettyServerHandler extends ChannelDuplexHandler {

    private static final Logger log = LoggerFactory.getLogger(NettyServerHandler.class);

    private final Map<String, Channel> channels = new ConcurrentHashMap<>();

    private final boolean http;

    public NettyServerHandler() {
        http = true;
    }

    public NettyServerHandler(boolean http) {
        this.http = http;
    }

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
        // TODO 对于HTTP连接，个人感觉没有必要添加进来，但是在这里好像也判断不了
        log.info("NettyServerHandler" + (http ? "Http " : "WebSocket ") + " >>> 接收到新的请求");
        ctx.fireChannelRead(msg);
        io.netty.channel.Channel channel = (io.netty.channel.Channel) msg;
        channels.put(channel.id().asShortText(), new NettyChannel(channel));
    }

    public Map<String, Channel> getChannelMap() {
        // Producer extends Consumer super
        return this.channels;
    }

}
