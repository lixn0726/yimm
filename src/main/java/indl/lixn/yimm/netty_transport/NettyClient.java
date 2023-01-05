package indl.lixn.yimm.netty_transport;

import indl.lixn.yimm.transport.Channel;
import indl.lixn.yimm.transport.base.BaseClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

import static indl.lixn.yimm.netty_transport.NettyConstant.RETRY_FREQUENCY;

/**
 * @author lixn
 * @description
 * @date 2023/01/04 10:00
 **/
public class NettyClient extends BaseClient {

    private static final Logger log = LoggerFactory.getLogger(NettyClient.class);

    private final Bootstrap bootstrap;
    private final EventLoopGroup worker;
    private NettyChannel wrappedChannel;
    private volatile boolean connected = false;

    
    public NettyClient() {
        this.bootstrap = new Bootstrap();
        this.worker = new NioEventLoopGroup();
        setUpBootstrap();
    }

    public void setUpBootstrap() {
        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast("Client Log Handler", new LoggingHandler());
                    }
                });
    }

    @Override
    public void connect(InetSocketAddress remote) {
        super.connect(remote);

        final ChannelFuture connectFuture = bootstrap.connect(remote);
        io.netty.channel.Channel originChannel = connectFuture.channel();

        this.wrappedChannel = new NettyChannel(originChannel);

        connectFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                if (future.isSuccess()) {
                    // Connection attempt succeed.Begin to accept incoming traffic.
                    connected = true;
                    log.info("NettyClient链接成功");
                } else {
                    // Close the connection if the connection attempt has failed.
                    originChannel.close();
                    if (!connected) {
                        log.info("Attempt to connect within " + ((double)RETRY_FREQUENCY / (double)1000) + " seconds");
                        try {
                            Thread.sleep(RETRY_FREQUENCY);
                        } catch (InterruptedException e) {
                            log.error(e.getMessage());
                        }
                        bootstrap.connect(remote).addListener(this);
                    }
                }
            }
        });
    }

    @Override
    public void send(Object data) {
        this.wrappedChannel.send(data);
    }

    @Override
    public void close() {
        this.wrappedChannel.close();
    }

    @Override
    public boolean isClosed() {
        return this.wrappedChannel.isClosed();
    }

    @Override
    protected Channel getChannel() {
        return this.wrappedChannel;
    }
}
