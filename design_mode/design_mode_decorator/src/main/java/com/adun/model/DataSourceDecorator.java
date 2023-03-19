package com.adun.model;

/**
 * 抽象基础装饰
 * @author ADun
 * @date 2022/12/31 0:32
 */
public class DataSourceDecorator implements DataSource {
    /**
     * 被封装组件
     */
    private DataSource wrappee;

    public DataSourceDecorator(DataSource source) {
        this.wrappee = source;
    }

    @Override
    public void writeData(String data) {
        wrappee.writeData(data);
    }

    @Override
    public String readData() {
        return wrappee.readData();
    }
}
