package com.adun.test_ThreadException;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ADun
 * @date 2023/6/4 14:28
 */
@Slf4j
@SpringBootTest
public class ThreadException1Tests {



    public static void main(String[] args) {
        try {
            Thread thread = new Thread(() -> {
                log.info("子线程抛出异常");
                throw new RuntimeException("运行时子线程抛出异常");
            },"test_sub_Thread");
            thread.start();
        } catch (Exception e) {
            log.error("异常发生", e);
        }
    }


}
