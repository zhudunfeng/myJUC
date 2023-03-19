package com.adun.model;

import java.util.Base64;

/**
 * 加密装饰
 * @author ADun
 * @date 2022/12/31 0:34
 */
public class EncryptionDecorator extends DataSourceDecorator {
    public EncryptionDecorator(DataSource source) {
        super(source);
    }

    @Override
    public void writeData(String data) {
        // 1. 对传递数据进行加密。
        // 2. 将加密后数据传递给被封装对象 writeData（写入数据）方法。
        super.writeData(encode(data));
    }

    @Override
    public String readData() {
        // 1. 通过被封装对象的 readData（读取数据）方法获取数据。
        // 2. 如果数据被加密就尝试解密。
        // 3. 返回结果。
        return decode(super.readData());
    }

    /**
     * 加密
     * @param data
     * @return
     */
    private String encode(String data){
        byte[] result = data.getBytes();
        for (int i = 0; i < result.length; i++) {
            result[i]+=(byte)1;
        }
        return Base64.getEncoder().encodeToString(result);
    }

    /**
     * 解密
     * @param data
     * @return
     */
    private String decode(String data){
        byte[] result = Base64.getDecoder().decode(data);
        for (int i = 0; i < result.length; i++) {
            result[i]-=(byte)1;
        }
        return new String(result);
    }
}
