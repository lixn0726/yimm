package indl.lixn.yimm.transport;

import java.net.InetSocketAddress;
import java.util.Collection;

/**
 * @author lixn
 * @description
 * @date 2023/01/03 10:08
 **/
public interface Server extends Endpoint {

    /**
     * 绑定本地端口
     * @param bindAddress 地址
     */
    void bind(InetSocketAddress bindAddress);

    /**
     * 获取所有活跃的channel
     * @return channel集合
     */
    Collection<? extends Channel> getChannels();

    /**
     * 根据id获取channel
     * @param channelId 通道id
     * @return channel对象
     */
    Channel getChannel(String channelId);

}
