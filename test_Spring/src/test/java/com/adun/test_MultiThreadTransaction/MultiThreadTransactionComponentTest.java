package com.adun.test_MultiThreadTransaction;


import com.adun.test_MultiThreadTransaction.MultiThreadTransactionComponent;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 多线程异步处理时的事务管理
 */
@SpringBootTest
public class MultiThreadTransactionComponentTest {

    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    public void setPlatformTransactionManager(PlatformTransactionManager platformTransactionManager) {
        this.platformTransactionManager = platformTransactionManager;
    }

    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    public void setThreadPoolTaskExecutor(ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }

    @Test
    public void testTransaction() {
        System.out.println();
        // 定义此主线程的异步处理时事务管理
        MultiThreadTransactionComponent mt = new MultiThreadTransactionComponent(platformTransactionManager, threadPoolTaskExecutor);
        for (int k = 0; k < 10; k++) {
            int i = RandomUtils.nextInt(0, 5);
            int y = RandomUtils.nextInt(0, 5);
            // 添加任务
            mt.addFunction(() -> {
                System.out.println("当前线程：" + Thread.currentThread().getName());
                System.out.println(i + "--" + y);
                //除数为0时执⾏失败
                System.out.println(i % y);
                return 0;
            });
        }
        // 执⾏任务
        mt.execute();
    }
}
