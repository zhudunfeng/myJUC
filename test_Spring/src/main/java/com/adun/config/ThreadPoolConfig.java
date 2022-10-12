package com.adun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ArrayBlockingQueue;
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
                , TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
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



}
