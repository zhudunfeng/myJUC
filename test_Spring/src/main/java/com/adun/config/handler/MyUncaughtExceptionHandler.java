package com.adun.config.handler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ADun
 * @date 2023/6/4 15:19
 */
@Slf4j
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        log.error("Exception in thread {}",t.getName(), e);
    }
}
