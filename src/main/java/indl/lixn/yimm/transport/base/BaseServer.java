package indl.lixn.yimm.transport.base;

import indl.lixn.yimm.transport.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * @author lixn
 * @description
 * @date 2023/01/03 10:12
 **/
public abstract class BaseServer implements Server {

    private static final Logger log = LoggerFactory.getLogger(BaseServer.class);

    @Override
    public void bind(InetSocketAddress bindAddress) {
        if (bindAddress == null) {
            log.error("Address is null. Check again.");
            return;
        }
        if (bindAddress.isUnresolved()) {
            log.error("Address {} cannot resolved. Check and try again.", bindAddress);
        }
    }

}
