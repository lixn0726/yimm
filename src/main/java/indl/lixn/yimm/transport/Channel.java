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

    boolean isConnected();

    Serialization getSerialization();

}
