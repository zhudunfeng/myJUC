package com.adun.observer.listeners;

import java.io.File;

/**
 * @author Zhu Dunfeng
 * @date 2024/3/17 11:21
 */
public class LogOpenListener implements EventListener{
    private File log;

    public LogOpenListener(String fileName) {
        this.log = new File(fileName);
    }

    @Override
    public void update(String eventType, File file) {
        System.out.println("Save to log " + log + ": Someone has performed " + eventType + " operation with the following file: " + file.getName());
    }
}
