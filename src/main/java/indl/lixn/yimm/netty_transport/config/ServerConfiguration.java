package indl.lixn.yimm.netty_transport.config;

import indl.lixn.yimm.common.Tuple;
import indl.lixn.yimm.common.util.ObjectUtils;
import indl.lixn.yimm.netty_transport.constant.NettyConstant;
import indl.lixn.yimm.netty_transport.enums.ServerProtocolType;
import indl.lixn.yimm.netty_transport.option.ChannelOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lixn
 * @description NettyServer配置类
 * @date 2023/01/06 10:40
 **/
public final class ServerConfiguration {

    private static final Logger log = LoggerFactory.getLogger(ServerConfiguration.class);

    public static ServerConfiguration of() {
        return new ServerConfiguration();
    }

    /** ServerChannel配置 */
    private List<Tuple<ChannelOption, Object>> serverChannelOptions = new ArrayList<>(16);

    /** ChildChannel配置 */
    private List<Tuple<ChannelOption, Object>> childChannelOptions = new ArrayList<>(16);

    private String listenAddress = NettyConstant.LOCAL_HOST;

    private int listenPort = NettyConstant.DEFAULT_PORT;

    private int bossNum = NettyConstant.BOSS_THREAD_NUM;

    private int workerNum = NettyConstant.WORKER_THREAD_NUM;

    private boolean epollActive = NettyConstant.EPOLL_ACTIVE;

    private ServerProtocolType protocol = ServerProtocolType.HTTP;

    public ServerProtocolType getProtocol() {
        return protocol;
    }

    public void setProtocol(ServerProtocolType protocol) {
        this.protocol = protocol;
    }

    public List<Tuple<ChannelOption, Object>> getServerChannelOptions() {
        return serverChannelOptions;
    }

    public void setServerChannelOptions(List<Tuple<ChannelOption, Object>> serverChannelOptions) {
        this.serverChannelOptions = serverChannelOptions;
    }

    public List<Tuple<ChannelOption, Object>> getChildChannelOptions() {
        return childChannelOptions;
    }

    public void setChildChannelOptions(List<Tuple<ChannelOption, Object>> childChannelOptions) {
        this.childChannelOptions = childChannelOptions;
    }

    public String getListenAddress() {
        return listenAddress;
    }

    public void setListenAddress(String listenAddress) {
        this.listenAddress = listenAddress;
    }

    public int getListenPort() {
        return listenPort;
    }

    public void setListenPort(int listenPort) {
        this.listenPort = listenPort;
    }

    public int getBossNum() {
        return bossNum;
    }

    public void setBossNum(int bossNum) {
        this.bossNum = bossNum;
    }

    public int getWorkerNum() {
        return workerNum;
    }

    public void setWorkerNum(int workerNum) {
        this.workerNum = workerNum;
    }

    public boolean isEpollActive() {
        return epollActive;
    }

    public void setEpollActive(boolean epollActive) {
        this.epollActive = epollActive;
    }

    public void addServerChannelOption(ChannelOption channelOption, Object val) {
        if (ObjectUtils.isNull(channelOption) || ObjectUtils.isNull(val)) {
            log.error("ServerConfiguration >>> 添加的Tuple<ChannelOption, Object>存在空值");
        }

        serverChannelOptions.add(new Tuple<>(channelOption, val));
    }

    public void addChildChannelOption(ChannelOption channelOption, Object val) {
        if (ObjectUtils.isNull(channelOption) || ObjectUtils.isNull(val)) {
            log.error("ServerConfiguration >>> 添加的Tuple<ChannelOption, Object>存在空值");
        }

        childChannelOptions.add(new Tuple<>(channelOption, val));
    }

}
