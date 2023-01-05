package indl.lixn.yimm.transport.base;

import indl.lixn.yimm.transport.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    protected abstract void send(byte[] data);

}
