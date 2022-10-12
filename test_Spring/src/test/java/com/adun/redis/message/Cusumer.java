package com.adun.redis.message;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author Zhu Dunfeng
 * @date 2022/5/9 21:51
 */
@SpringBootTest
public class Cusumer {

    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    public void setThreadPoolTaskExecutor(ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }

    @Test
    public void getMessage(){
        System.out.println();
    }
}
