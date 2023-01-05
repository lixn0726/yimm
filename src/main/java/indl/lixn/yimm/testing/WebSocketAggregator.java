package indl.lixn.yimm.testing;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author lixn
 * @description
 * @date 2023/01/05 15:34
 **/
public class WebSocketAggregator {

    public static ChannelInitializer<SocketChannel> initializer() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new LoggingHandler());
            }
        };
    }

}
