package com.adun.observer.publisher;

import com.adun.observer.listeners.EventListener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础发布者
 * <p>
 * 事件管理器
 *
 * @author Zhu Dunfeng
 * @date 2024/3/17 11:07
 */
public class EventManager {

    private Map<String, List<EventListener>> listeners = new HashMap<>();

    public EventManager(String... operations) {
        for (String operation : operations) {
            // 初始化监听器管理对象
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    /**
     * 消息订阅
     *
     * @param eventType 事件类型
     * @param listener  对应事件的监听器
     */
    public void subscribe(String eventType, EventListener listener) {
        List<EventListener> eventListeners = this.listeners.get(eventType);
        eventListeners.add(listener);
    }

    /**
     * 取消订阅
     *
     * @param eventType
     * @param listener
     */
    public void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> eventListeners = this.listeners.get(eventType);
        eventListeners.remove(listener);
    }

    /**
     * 消息通知
     *
     *  通知订阅当前事件的监听器，进行文件更新
     * @param eventType
     * @param file
     */
    public void notify(String eventType, File file) {
        List<EventListener> eventListeners = this.listeners.get(eventType);
        for (EventListener listener : eventListeners) {
            listener.update(eventType,file);
        }
    }
}
