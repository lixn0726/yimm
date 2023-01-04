package indl.lixn.yimm.transport;

import indl.lixn.yimm.serialization.Serialization;

import java.net.InetSocketAddress;

/**
 * @author lixn
 * @description
 * @date 2023/01/03 10:08
 **/
public interface Channel extends Endpoint {

    String id();

    void connect(InetSocketAddress remote);

    default void connect(String hostname, int port) {
        connect(new InetSocketAddress(hostname, port));
    }

    boolean isConnected();

    Serialization getSerialization();

}
