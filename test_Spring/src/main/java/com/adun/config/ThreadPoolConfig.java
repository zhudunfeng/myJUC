package com.adun.config;

import com.adun.config.decorator.MyThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Zhu Dunfeng
 * @date 2022/4/13 16:19
 */
@Configuration
public class ThreadPoolConfig {

    /**
     * 使用仓壁模型
     * @return
     */
    @Bean
    public ThreadPoolExecutor threadPoolExecutor(){
        return new ThreadPoolExecutor(100, 200, 20
                , TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), Executors.defaultThreadFactory());
    }

    @Bean
    public ThreadPoolExecutor threadPoolExecutor2(){
        return new ThreadPoolExecutor(100, 200, 20
                , TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
    }

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        return new ThreadPoolTaskExecutor();
    }


    @Bean("webSocketExecutor")
    public ThreadPoolTaskExecutor websocketExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(16);
        executor.setMaxPoolSize(16);
        //支持同时推送1000人
        executor.setQueueCapacity(1000);
        executor.setThreadNamePrefix("websocket-executor-");
        //满直接丢弃
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        executor.initialize();
        executor.getThreadPoolExecutor().setThreadFactory(new MyThreadFactory(executor));
        return executor;
    }


}
