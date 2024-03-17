package com.adun.test_ThreadException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ADun
 * @date 2023/6/4 14:28
 */
@Slf4j
@SpringBootTest
public class ThreadException2Tests {


    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            log.info("子线程抛出异常");
            throw new RuntimeException("运行时子线程抛出异常");
        }, "test_sub_Thread");
        //为Thread绑定自定义异常捕获处理器
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = (t, e) -> {
            log.error("Exception in thread {}",t.getName(), e);
        };
        thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
        thread.start();
    }


}
