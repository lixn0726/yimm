package indl.lixn.yimm.transport;

import java.net.InetSocketAddress;

/**
 * @author lixn
 * @description
 * @date 2023/01/03 10:07
 **/
public interface Client extends Endpoint {

    /**
     * 连接服务
     * @param remote 服务ip地址
     */
    void connect(InetSocketAddress remote);

    /**
     * 连接服务
     * @param hostname ip
     * @param port 端口
     */
    default void connect(String hostname, int port) {
        connect(new InetSocketAddress(hostname, port));
    }

    /**
     * client是否处于联通的状态
     * @return true/false
     */
    boolean isConnected();

}
