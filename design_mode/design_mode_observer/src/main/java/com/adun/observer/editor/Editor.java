package com.adun.observer.editor;

import com.adun.observer.publisher.EventManager;

import java.io.File;

/**
 * 具体发布者， 由其他对象追踪
 *
 * @author Zhu Dunfeng
 * @date 2024/3/17 11:24
 */
public class Editor {

    public EventManager eventManager;

    private File file;

    public Editor() {
        this.eventManager = new EventManager("open", "save");
    }

    /**
     * 打开对应路径问及爱你
     *
     * @param filePath
     */
    public void openFile(String filePath) {
        this.file = new File(filePath);
        // 通知open事件订阅者
        eventManager.notify("open", file);
    }

    /**
     * 保存文件
     *
     * @throws Exception
     */
    public void saveFile() throws Exception {
        if (this.file != null) {
            // 通知save事件订阅者
            eventManager.notify("save", file);
        } else {
            throw new Exception("Please open a file first.");
        }
    }

}
