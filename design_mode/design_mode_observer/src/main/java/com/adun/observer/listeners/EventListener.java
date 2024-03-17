package com.adun.observer.listeners;

import java.io.File;

/**
 * 通用观察者接口
 * 事件监听器
 *
 * @author Zhu Dunfeng
 * @date 2024/3/17 11:08
 */
public interface EventListener {

    void update(String eventType, File file);

}
