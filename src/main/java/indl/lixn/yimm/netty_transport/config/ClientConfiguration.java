package indl.lixn.yimm.netty_transport.config;

import indl.lixn.yimm.netty_transport.constant.NettyConstant;

/**
 * @author lixn
 * @description
 * @date 2023/01/06 10:47
 **/
public final class ClientConfiguration {

    private String connectAddress = NettyConstant.LOCAL_HOST;

    private int connectPort = NettyConstant.DEFAULT_PORT;

    private int workerNum = NettyConstant.WORKER_THREAD_NUM;

    private boolean epollActive = NettyConstant.EPOLL_ACTIVE;



}
