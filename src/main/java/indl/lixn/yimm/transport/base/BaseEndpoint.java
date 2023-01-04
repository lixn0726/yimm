package indl.lixn.yimm.transport.base;

import indl.lixn.yimm.transport.Endpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lixn
 * @description
 * @date 2023/01/03 10:13
 **/
public abstract class BaseEndpoint implements Endpoint {

    private static final Logger log = LoggerFactory.getLogger(BaseEndpoint.class);

    @Override
    public void send(Object data) {
        // 统一做 isClosed判断。因为是一段通用的逻辑
        if (isClosed()) {
            log.error("Endpoint is closed. Cannot send msg anymore.");
        }
    }

}
