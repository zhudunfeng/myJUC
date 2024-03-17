package com.adun.config.decorator;

import com.adun.config.handler.MyUncaughtExceptionHandler;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.concurrent.ThreadFactory;

/**
 * @author ADun
 * @date 2023/6/4 15:17
 */
@AllArgsConstructor
public class MyThreadFactory implements ThreadFactory {

    private ThreadFactory original;

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = original.newThread(r);
        thread.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        return thread;
    }
}
