package indl.lixn.yimm.netty_transport.option;

import indl.lixn.yimm.common.Tuple;
import indl.lixn.yimm.common.util.ObjectUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lixn
 * @description
 * @date 2023/01/06 10:25
 **/
public class ChannelOptionConfigurator {

    private static final Logger log = LoggerFactory.getLogger(ChannelOptionConfigurator.class);

    public static void setUpClientChannelOption(Bootstrap bootstrap, Tuple<ChannelOption, Object> kvTuple) {
        if (ObjectUtils.hasNull(bootstrap, kvTuple)) {
            return;
        }
        log.info("ChannelOptionConfigurator >>> 装配Client ChannelOption：{}, {}", kvTuple.getLeft().value, kvTuple.getRight());
        bootstrap.option(io.netty.channel.ChannelOption.valueOf(kvTuple.getLeft().value), kvTuple.getRight());
    }

    public static void setUpServerChannelOption(ServerBootstrap serverBootstrap, Tuple<ChannelOption, Object> kvTuple) {
        if (ObjectUtils.hasNull(serverBootstrap, kvTuple)) {
            return;
        }
        log.info("ChannelOptionConfigurator >>> 装配Server ChannelOption：{}, {}", kvTuple.getLeft().value, kvTuple.getRight());
        serverBootstrap.option(io.netty.channel.ChannelOption.valueOf(kvTuple.getLeft().value), kvTuple.getRight());
    }

    public static void setUpServerChildChannelOption(ServerBootstrap serverBootstrap, Tuple<ChannelOption, Object> kvTuple) {
        if (ObjectUtils.hasNull(serverBootstrap, kvTuple)) {
            return;
        }
        log.info("ChannelOptionConfigurator >>> 装配Server ChildChannelOption：{}, {}", kvTuple.getLeft().value, kvTuple.getRight());
        serverBootstrap.childOption(io.netty.channel.ChannelOption.valueOf(kvTuple.getLeft().value), kvTuple.getRight());
    }

}
