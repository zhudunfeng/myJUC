package com.adun.test_ThreadException;

import cn.hutool.core.thread.NamedThreadFactory;
import com.adun.config.handler.MyUncaughtExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ADun
 * @date 2023/6/4 14:28
 */
@Slf4j
@SpringBootTest
public class ThreadException4Tests {

    @Qualifier("webSocketExecutor")
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Test
    public void execute(){
        threadPoolTaskExecutor.execute(()->{
            log.info("子线程抛出异常");
            throw new RuntimeException("运行时子线程抛出异常");
        });
    }


}
