package indl.lixn.yimm.testing;

import indl.lixn.yimm.netty_transport.handler.NettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author lixn
 * @description
 * @date 2023/01/05 15:38
 **/
public class TestingNettyServer {

    public static ServerBootstrap http(InetSocketAddress remote) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        EventLoopGroup worker = new NioEventLoopGroup();
        EventLoopGroup boss = new NioEventLoopGroup(1);
        try {
            serverBootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new NettyServerHandler(true))
                    .childHandler(HttpAggregator.initializer());

            serverBootstrap.bind(remote).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("HttpServer绑定成功");
                    } else {
                        System.out.println("HttpServer绑定失败。" + future.cause());
                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return serverBootstrap;
    }

    public static ServerBootstrap webSocket(InetSocketAddress remote) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        EventLoopGroup worker = new NioEventLoopGroup();
        EventLoopGroup boss = new NioEventLoopGroup(1);
        try {
            serverBootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new NettyServerHandler(false))
                    .childHandler(WebSocketAggregator.initializer());

            serverBootstrap.bind(remote).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("WebSocketServer绑定成功");
                    } else {
                        System.out.println("WebSocketServer绑定失败。" + future.cause());
                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return serverBootstrap;
    }
}
