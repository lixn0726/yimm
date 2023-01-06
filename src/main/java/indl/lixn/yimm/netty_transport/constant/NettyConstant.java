package indl.lixn.yimm.netty_transport.constant;

/**
 * @author lixn
 * @description
 * @date 2023/01/06 10:24
 **/
public interface NettyConstant {

    boolean EPOLL_ACTIVE = false;

    int BOSS_THREAD_NUM = 1;

    int WORKER_THREAD_NUM = Runtime.getRuntime().availableProcessors() * 2 + 1;

    int DEFAULT_PORT = 0;

    String LOCAL_HOST = "127.0.0.1";

}
