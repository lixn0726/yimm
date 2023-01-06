package indl.lixn.yimm.netty_transport;

import indl.lixn.yimm.common.Tuple;
import indl.lixn.yimm.common.util.ObjectUtils;
import indl.lixn.yimm.netty_transport.config.ServerConfiguration;
import indl.lixn.yimm.netty_transport.enums.ServerProtocolType;
import indl.lixn.yimm.netty_transport.handler.NettyServerHandler;
import indl.lixn.yimm.netty_transport.option.ChannelOption;
import indl.lixn.yimm.netty_transport.option.ChannelOptionConfigurator;
import indl.lixn.yimm.transport.Channel;
import indl.lixn.yimm.transport.base.BaseServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Map;

/**
 * @author lixn
 * @description
 * @date 2023/01/04 09:56
 **/
public class NettyServer extends BaseServer {

    private static final Logger log = LoggerFactory.getLogger(NettyServer.class);

    private final ServerConfiguration config;

    private final ServerBootstrap serverBootstrap;
    private final EventLoopGroup boss;
    private final EventLoopGroup worker;

    private final NettyServerHandler serverHandler = new NettyServerHandler();
    private final Map<String, Channel> idChannelMap = serverHandler.getChannelMap();
    private NettyChannel channel;

    private final ServerProtocolType handleProtocol;

    private volatile boolean bind = false;
    private volatile boolean closed = true;
    private volatile boolean hasOption = false;
    private volatile boolean hasChildOption = false;
    private volatile boolean hasHandler = false;
    private volatile boolean hasChildHandler = false;

    public NettyServer(ServerConfiguration config) {
        this.config = config;
        serverBootstrap = new ServerBootstrap();
        boss = config.isEpollActive() ? new EpollEventLoopGroup(config.getBossNum()) : new NioEventLoopGroup(config.getBossNum());
        worker = config.isEpollActive() ? new EpollEventLoopGroup(config.getWorkerNum()) : new NioEventLoopGroup(config.getWorkerNum());
        handleProtocol = config.getProtocol();
        setUpServerBootstrapAndBind();
    }

    public String id() {
        return this.handleProtocol.name + "-" + this.channel.id();
    }

    private void setUpServerBootstrapAndBind() {
        this.serverBootstrap.group(boss, worker)
                .channel(config.isEpollActive() ? EpollServerSocketChannel.class : NioServerSocketChannel.class);
        setUpChannelOption();
        setUpChildChannelOption();
        setUpHandler();
        setUpChildHandler();
        bind(config.getListenAddress(), config.getListenPort());
    }

    private void setUpChannelOption() {
        if (config.getServerChannelOptions().size() != 0) {
            hasOption = true;
            for (Tuple<ChannelOption, Object> tuple : config.getServerChannelOptions()) {
                ChannelOptionConfigurator.setUpServerChannelOption(this.serverBootstrap, tuple);
            }
        }
    }

    private void setUpChildChannelOption() {
        if (config.getChildChannelOptions().size() != 0) {
            hasChildOption = true;
            for (Tuple<ChannelOption, Object> tuple : config.getChildChannelOptions()) {
                ChannelOptionConfigurator.setUpServerChildChannelOption(this.serverBootstrap, tuple);
            }
        }
    }
    
    private void setUpChildHandler() {
        hasChildHandler = true;
    }
    
    private void setUpHandler() {
        hasHandler = true;
    }
    
    @Override
    public void bind(InetSocketAddress bindAddress) {
        super.bind(bindAddress);
        if (bind) {
            log.error("NettyServer >>> 已处于监听状态，无法重复监听");
            return;
        }
        if (!configSetUpComplete()) {
            log.error("NettyServer >>> ServerBootstrap配置不完全");
            return;
        }
        final ChannelFuture bindFuture = serverBootstrap.bind(bindAddress);
        io.netty.channel.Channel originChannel = bindFuture.channel();

        bindFuture.addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                bind = true;
                closed = false;
                this.channel = new NettyChannel(originChannel);
                log.info("NettyServer >>> 监听 {} 成功", bindAddress);
            }
        });
    }

    @Override
    public void send(Object data) {
        if (closed) {
            log.error("NettyServer >>> 处于关闭状态，无法发送");
            return;
        }
        if (getChannels().size() == 0) {
            log.error("NettyServer >>> 连接数为0，无法发送");
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
            log.error("NettyServer >>> 关闭时出现异常");
            return;
        }
        log.info("NettyServer >>> 关闭成功");
        closed = true;
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
    public boolean isClosed() {
        return this.closed;
    }

    private boolean configSetUpComplete() {
        return hasChildOption
                && hasChildHandler
                && hasOption
                && hasHandler;
    }

    public Map<String, Channel> getIdChannelMap() {
        return idChannelMap;
    }

    public ServerProtocolType getHandleProtocol() {
        return this.handleProtocol;
    }

    @Override
    public int hashCode() {
        // 在new完之后，config、channel都不为空，所以这么写应该是没问题的
        int result = 0;
        result += config.hashCode();
        result += channel.id().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (ObjectUtils.isNull(obj)) {
            return false;
        }
        if (!(obj instanceof NettyServer)) {
            return false;
        }
        NettyServer other = (NettyServer) obj;
        return other.config.equals(this.config)
                && other.channel.equals(this.channel);
    }
}
