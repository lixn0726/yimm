package indl.lixn.yimm.application;

import indl.lixn.yimm.netty_transport.NettyServer;
import indl.lixn.yimm.transport.Channel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lixn
 * @description
 * @date 2023/01/06 10:08
 **/
public class Configuration {

    private Map<NettyServer, Map<String, Channel>> channels;

    private final List<Channel> channelList = new ArrayList<>();

    public Channel getChannel() {
        return null;
    }

}
