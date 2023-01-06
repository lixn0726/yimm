package indl.lixn.yimm.netty_transport.facade;

import indl.lixn.yimm.netty_transport.NettyServer;
import indl.lixn.yimm.netty_transport.config.ServerConfiguration;

import java.util.function.Function;

/**
 * @author lixn
 * @description
 * @date 2023/01/06 15:28
 **/
public class WrappedNettyStarter {

    private static final Function<ServerConfiguration, NettyServer> serverCreator = NettyServer::new;

    public static NettyServer createServer(ServerConfiguration config) {
        return serverCreator.apply(config);
    }

}
