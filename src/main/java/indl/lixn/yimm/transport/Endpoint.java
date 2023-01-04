package indl.lixn.yimm.transport;

/**
 * @author lixn
 * @description
 * @date 2023/01/03 10:08
 **/
public interface Endpoint {

    void send(Object data);

    void close();

    boolean isClosed();

}
