package indl.lixn.yimm.netty_transport.testing;

import indl.lixn.yimm.netty_transport.NettyClient;
import indl.lixn.yimm.netty_transport.NettyConstant;
import indl.lixn.yimm.netty_transport.NettyServer;

import java.net.InetSocketAddress;

/**
 * @author lixn
 * @description
 * @date 2023/01/04 14:35
 **/
public class Testing {

    private static final InetSocketAddress ADDRESS = new InetSocketAddress(NettyConstant.LOCALHOST, NettyConstant.DEFAULT_BIND_PORT);

    public static void main(String[] args) {

        NettyServer server = new NettyServer();

        NettyClient client = new NettyClient();
        client.connect(ADDRESS);

        client.send("hello server, can you response to me?");
    }

}
