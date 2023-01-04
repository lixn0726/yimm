package indl.lixn.yimm.netty_transport;

import indl.lixn.yimm.netty_transport.handler.NettyServerHandler;
import indl.lixn.yimm.transport.Channel;
import indl.lixn.yimm.transport.base.BaseServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Map;

import static indl.lixn.yimm.netty_transport.NettyConstant.DEFAULT_BIND_PORT;
import static indl.lixn.yimm.netty_transport.NettyConstant.LOCALHOST;

/**
 * @author lixn
 * @description
 * @date 2023/01/04 09:56
 **/
public class NettyServer extends BaseServer {

    private static final Logger log = LoggerFactory.getLogger(NettyServer.class);
    private static final InetSocketAddress DEFAULT_BIND_ADDRESS = new InetSocketAddress(LOCALHOST, DEFAULT_BIND_PORT);

    private final ServerBootstrap serverBootstrap;
    private final EventLoopGroup boss;
    private final EventLoopGroup worker;
    private final NettyServerHandler serverHandler = new NettyServerHandler();
    private final Map<String, Channel> idChannelMap = serverHandler.getChannelMap();

    private NettyChannel channel;

    private volatile boolean bind = false;
    private volatile boolean closed = true;

    public NettyServer() {
        this(DEFAULT_BIND_ADDRESS);
    }

    public NettyServer(InetSocketAddress remote) {
        serverBootstrap = new ServerBootstrap();
        boss = new NioEventLoopGroup(1);
        worker = new NioEventLoopGroup();
        setUpServerBootstrap();
        bind(remote);
    }

    private void setUpServerBootstrap() {
        this.serverBootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .handler(serverHandler)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast("Log Handler", new LoggingHandler());
                    }
                });
    }

    @Override
    public void bind(InetSocketAddress bindAddress) {
        super.bind(bindAddress);
        if (bind) {
            log.error("NettyServer已处于监听状态，无法重复监听");
            return;
        }
        final ChannelFuture bindFuture = serverBootstrap.bind(bindAddress);
        io.netty.channel.Channel originChannel = bindFuture.channel();

        bindFuture.addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                bind = true;
                closed = false;
                this.channel = new NettyChannel(originChannel);
                log.info("NettyServer监听 {} 成功", bindAddress);
            }
        });
    }

    @Override
    public Collection<? extends Channel> getChannels() {
        return this.idChannelMap.values();
    }

    @Override
    public Channel getChannel(String channelId) {
        return this.idChannelMap.get(channelId);
    }

    @Override
    public void send(Object data) {
        if (closed) {
            log.error("NettyServer处于关闭状态，无法发送");
            return;
        }
        if (getChannels().size() == 0) {
            log.error("NettyServer中的连接数为0，无法发送");
            return;
        }
        for (Channel channel : getChannels()) {
            channel.send(data);
        }
    }

    @Override
    public void close() {
        channel.close();
        try {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        } catch (Exception ex) {
            log.error("NettyServer关闭时出现异常");
            return;
        }
        log.info("NettyServer关闭成功");
        closed = true;
    }

    @Override
    public boolean isClosed() {
        return this.closed;
    }
}
