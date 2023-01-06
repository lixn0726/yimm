package indl.lixn.yimm.netty_transport.handler;

import indl.lixn.yimm.common.util.ObjectUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author lixn
 * @description
 * @date 2023/01/06 11:03
 **/
public final class ChannelHandlerConfigurator {

    private static final Logger log = LoggerFactory.getLogger(ChannelHandlerConfigurator.class);

    public static void setUpServerChildHandler(ServerBootstrap serverBootstrap, List<ChannelHandler> handlers) {
        if (ObjectUtils.hasNull(serverBootstrap, handlers)) {
            log.error("ChannelHandlerConfigurator >>> ServerBootstrap或 handlers为null");
            return;
        }
        handlers.forEach(serverBootstrap::childHandler);
    }

    public static void setUpServerHandler(ServerBootstrap serverBootstrap, List<ChannelHandler> handlers) {
        if (ObjectUtils.hasNull(serverBootstrap, handlers)) {
            log.error("ChannelHandlerConfigurator >>> ServerBootstrap或 handlers为null");
            return;
        }
        handlers.forEach(serverBootstrap::handler);
    }

    public static void setUpClientHandler(Bootstrap bootstrap, List<ChannelHandler> handlers) {
        if (ObjectUtils.hasNull(bootstrap, handlers)) {
            log.error("ChannelHandlerConfigurator >>> Bootstrap或 handlers为null");
            return;
        }
        handlers.forEach(bootstrap::handler);
    }

}
