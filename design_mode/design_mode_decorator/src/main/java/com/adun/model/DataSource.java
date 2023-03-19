package com.adun.model;

/**
 *  定义了读取和写入操作的通用数据接口
 * @author ADun
 * @date 2022/12/31 0:25
 */
public interface DataSource {
    /**
     * 写入数据
     * @param data
     */
    void writeData(String data);

    /**
     * 读取数据
     * @return
     */
    String readData();
}
