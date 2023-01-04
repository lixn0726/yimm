package indl.lixn.yimm.transport.base;

import indl.lixn.yimm.transport.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * @author lixn
 * @description
 * @date 2023/01/03 10:14
 **/
public abstract class BaseChannel implements Channel {

    private static final Logger log = LoggerFactory.getLogger(BaseChannel.class);

    @Override
    public void send(Object data) {
        send(getSerialization().encode(data));
    }

    @Override
    public void connect(InetSocketAddress remote) {
        if (isClosed()) {
            log.error("Channel not initiate. Try again.");
        }
        if (isConnected()) {
            log.error("Channel is connected. Not allow operation.");
        }
    }

    protected abstract void send(byte[] data);

}
