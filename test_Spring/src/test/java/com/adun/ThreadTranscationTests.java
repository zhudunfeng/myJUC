package com.adun;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Zhu Dunfeng
 * @date 2022/6/1 19:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class ThreadTranscationTests {

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;


    @Test
    public void enterMain() throws Exception {

        CopyOnWriteArrayList<Future> futures = new CopyOnWriteArrayList<>();

        AtomicInteger atomicInteger = new AtomicInteger(5);
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            atomicInteger.getAndIncrement();
        }, threadPoolExecutor);
        futures.add(future1);

        CompletableFuture<Void> future2 = CompletableFuture.runAsync(()->{
            atomicInteger.getAndIncrement();
        },threadPoolExecutor);
        futures.add(future2);

        CompletableFuture<Void> future3 = CompletableFuture.runAsync(()->{
            atomicInteger.getAndIncrement();
            int i=10/0;
        },threadPoolExecutor);
        futures.add(future3);

        transactionThread(futures);

        System.out.println(atomicInteger.get());
    }


    public void transactionThread(CopyOnWriteArrayList<Future> futures) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(futures.size());
        for (Future future : futures) {
            try {
                future.get();
                System.out.println("countDownLatch.getCount() = " + countDownLatch.getCount());
                countDownLatch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
                if(countDownLatch.getCount()>0){
                     System.out.println("异常退出");
                     futures.clear();
                     return;
                }
            }
        }
        futures.clear();
        countDownLatch.await();
    }


    public  void add(AtomicInteger number){
        number.getAndIncrement();
    }


}
