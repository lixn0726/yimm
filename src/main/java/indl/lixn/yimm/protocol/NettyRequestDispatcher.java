package indl.lixn.yimm.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author lixn
 * @description
 * @date 2023/01/05 10:21
 **/
public class NettyRequestDispatcher implements RequestDispatcher<ByteBuf> {

    private static NettyHttpRequestHandler httpRequestHandler;
    private static NettyWebSocketDispatcher webSocketDispatcher;

    @Override
    public void dispatch(ByteBuf incomeRequest) {

    }

    private static class NettyHttpRequestHandler {
        public void handle(FullHttpRequest req) {

        }
    }

    private static class NettyWebSocketDispatcher {

    }
}
