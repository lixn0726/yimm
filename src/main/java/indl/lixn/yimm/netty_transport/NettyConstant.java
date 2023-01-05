package indl.lixn.yimm.netty_transport;

/**
 * @author lixn
 * @description
 * @date 2023/01/04 11:12
 **/
public interface NettyConstant {

    int MAX_RETRY = 3;

    int DEFAULT_BIND_PORT = 8888;

    /** 重试频率，单位：ms */
    int RETRY_FREQUENCY = 1000;

    String LOCALHOST = "127.0.0.1";

    byte CR = (byte)(13);

    byte LF = (byte)(10);

}
