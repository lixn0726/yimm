package indl.lixn.yimm.netty_transport;

/**
 * @author lixn
 * @description
 * @date 2023/01/05 10:19
 **/
public class NettyServerCreator {

    public static NettyServer forWebSocket() {
        return new NettyServer();
    }

    public static NettyServer forHttp() {
        return new NettyServer();
    }

}
