package indl.lixn.yimm.testing;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpObjectAggregator;

/**
 * @author lixn
 * @description
 * @date 2023/01/05 15:35
 **/
public class HttpAggregator {


    /*
    HTTP有8种方法：
    OPTIONS：返回服务器针对特定资源所支持的方法，也可以利用向web服务器发送 '*'的请求来测试服务器的功能性
    HEAD：向服务器索要与GET请求相一致的响应，只不过响应体将不会被返回。这一方法可以在不必传输整个响应内容的情况下，就可以获取包含在响应消息头中的元信息
    GET：向特定的资源发出请求
    POST：向指定资源提交数据进行处理请求（例如提交表单或者上传文件）。数据被包含在请求体中。POST请求可能会导致新的资源的建立和/或已有资源的修改
    PUT：向指定资源位置上传其最新内容
    DELETE：请求服务器删除Request-URI所标识的资源
    TRACE：回显服务器收到的请求，主要用于测试或诊断
    CONNECT：HTTP/1.1协议中预留给能够将连接改为管道方式的代理服务器
    综上，需要用2个字节来区分这8个方法，因为拥有POST、PUT，第一个都是P
     */

    public static boolean isHttp(int magic1, int magic2) {
        return
                magic1 == 'G' && magic2 == 'E' || // GET
                        magic1 == 'P' && magic2 == 'O' || // POST
                        magic1 == 'P' && magic2 == 'U' || // PUT
                        magic1 == 'H' && magic2 == 'E' || // HEAD
                        magic1 == 'O' && magic2 == 'P' || // OPTIONS
                        magic1 == 'P' && magic2 == 'A' || // PATCH
                        magic1 == 'D' && magic2 == 'E' || // DELETE
                        magic1 == 'T' && magic2 == 'R' || // TRACE
                        magic1 == 'C' && magic2 == 'O';   // CONNECT
    }

    public static ChannelInitializer<SocketChannel> initializer() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
//                ch.pipeline().addLast(new LoggingHandler());
                ch.pipeline().addLast(new HttpObjectAggregator(10 * 1024));
                ch.pipeline().addLast(new EchoHttpHandler());
            }
        };
    }

    private static class EchoHttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
            System.out.println("EchoHttpHandler >>> 接收到 FullHttpRequest：" + msg);
        }

    }

}
