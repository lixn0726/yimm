package indl.lixn.yimm.application;

import org.springframework.context.annotation.Bean;

import java.util.concurrent.*;

/**
 * @author lixn
 * @description
 * @date 2023/01/06 09:23
 **/
public class ThreadPoolConfigs {

    @Bean(name = "dispatchers")
    public ExecutorService dispatchThreadPool() {
        return new ThreadPoolExecutor(10,
                10,
                (60 * 1000),
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1000),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setName("Dispatcher-Executor");
                        return t;
                    }
                }
        );
    }

}
