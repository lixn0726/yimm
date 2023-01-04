package indl.lixn.yimm.transport.base;

import indl.lixn.yimm.transport.Channel;
import indl.lixn.yimm.transport.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * @author lixn
 * @description
 * @date 2023/01/03 10:14
 **/
public abstract class BaseClient implements Client {

    private static final Logger log = LoggerFactory.getLogger(BaseClient.class);

    @Override
    public void connect(InetSocketAddress remote) {
        if (isConnected()) {
            log.error("Client is connected. Not allow operation.");
        }
    }

    @Override
    public boolean isConnected() {
        if (getChannel() == null) {
            return false;
        }
        return getChannel().isConnected();
    }

    protected abstract Channel getChannel();

}
