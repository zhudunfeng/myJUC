package com.adun.test_ThreadException;

import cn.hutool.core.thread.NamedThreadFactory;
import com.adun.config.handler.MyUncaughtExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

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
public class ThreadException3Tests {

    private static ExecutorService executor = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(500),
            new NamedThreadFactory("refresh-isDetail", null, false, new MyUncaughtExceptionHandler()));


    public static void main(String[] args) {
//        Thread thread = new Thread(() -> {
//            log.info("子线程抛出异常");
//            throw new RuntimeException("运行时子线程抛出异常");
//        }, "test_sub_Thread");
//        //为Thread绑定自定义异常捕获处理器
//        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = (t, e) -> {
//            log.error("Exception in thread {}",t.getName(), e);
//        };
//        thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
//        thread.start();
        executor.execute(() -> {
            log.info("子线程抛出异常");
            throw new RuntimeException("运行时子线程抛出异常");
        });
    }


}
