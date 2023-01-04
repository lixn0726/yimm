package indl.lixn.yimm.protocol;

import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author lixn
 * @description
 * @date 2023/01/04 15:58
 **/
public class NettyBasedDetector {

    private static final Logger log = LoggerFactory.getLogger(NettyBasedDetector.class);

    private static final int MIN_READABLE_LENGTH = 4;
    /** 回车 - /r */
    private static final byte[] ENTER_BYTES = new byte[] {47, 114};
    /** 换行 - /n */
    private static final byte[] LINE_BREAK_BYTES = new byte[] {47, 110};

    private ProtocolHandlerDispatcher dispatcher;

    public ProtocolType detectOuterProtocol(ByteBuf in) {
        if (in.readableBytes() < MIN_READABLE_LENGTH) {
            log.error("NettyTransport >>> 接收到不合规的数据包，抛弃");
            return ProtocolType.NONE;
        }
        return detectIncomeProtocol(in);
    }

    private ProtocolType detectIncomeProtocol(ByteBuf in) {
        byte[] data = new byte[in.readableBytes()];
        // Get income data as byte[]
        in.readBytes(data);
        
        return ProtocolType.NONE;
    }

    public static void main(String[] args) {
        String lineBreak = "/r";
        String enter = "/n";
        System.out.println(Arrays.toString(lineBreak.getBytes(StandardCharsets.UTF_8)));
        System.out.println(Arrays.toString(enter.getBytes(StandardCharsets.UTF_8)));
    }
}
