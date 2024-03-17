package com.adun.observer;

import com.adun.observer.editor.Editor;
import com.adun.observer.listeners.EmailNotificationListener;
import com.adun.observer.listeners.LogOpenListener;

/**
 * 在本例中， 观察者模式在文本编辑器的对象之间建立了间接的合作关系。
 * 每当 编辑器  （Editor） 对象改变时， 它都会通知其订阅者。
 * 邮件通知监听器  （EmailNotificationListener） 和 日志开启监听器  （LogOpenListener）
 * 都将通过执行其基本行为来对这些通知做出反应。
 *
 * 订阅者类不与编辑器类相耦合， 且能在需要时在其他应用中复用。
 *  编辑器类仅依赖于抽象订阅者接口。 这样就能允许在不改变编辑器代码的情况下添加新的订阅者类型。
 *
 * @author Zhu Dunfeng
 * @date 2024/3/17 11:29
 */
public class Demo {
    public static void main(String[] args) {
        Editor editor = new Editor();

        editor.eventManager.subscribe("open", new LogOpenListener("/path/to/log/file.txt"));
        editor.eventManager.subscribe("save", new EmailNotificationListener("admin@example.com"));

        try {
            editor.openFile("test.txt");
            editor.saveFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
