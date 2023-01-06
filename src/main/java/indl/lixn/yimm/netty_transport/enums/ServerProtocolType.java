package indl.lixn.yimm.netty_transport.enums;

/**
 * @author lixn
 * @description
 * @date 2023/01/06 10:56
 **/
public enum ServerProtocolType {

    /*
    协议类型
     */

    HTTP("HTTPServer"),
    WebSocket("WebSocketServer")
    ;

    public final String name;

    ServerProtocolType(String name) {
        this.name = name;
    }
}
