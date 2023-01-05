package indl.lixn.yimm.testing;

import io.netty.bootstrap.ServerBootstrap;

import java.net.InetSocketAddress;

/**
 * @author lixn
 * @description
 * @date 2023/01/05 15:39
 **/
public class Testing {

    public static void main(String[] args) {
        InetSocketAddress local = new InetSocketAddress("127.0.0.1", 8888);
        ServerBootstrap http = TestingNettyServer.http(local);
        ServerBootstrap ws = TestingNettyServer.webSocket(local);
//        http.bind(local);
//        ws.bind(local);
    }

}
