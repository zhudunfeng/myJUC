package com.adun.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/**
 * 压缩装饰
 * @author ADun
 * @date 2022/12/31 0:44
 */
public class CompressionDecorator extends DataSourceDecorator{

    /**
     * 压缩等级
     */
    private int compLevel = 6;

    public CompressionDecorator(DataSource source) {
        super(source);
    }

    @Override
    public void writeData(String data) {
        // 1. 压缩传递数据。
        // 2. 将压缩后数据传递给被封装对象 writeData（写入数据）方法。
        super.writeData(compress(data));
    }

    @Override
    public String readData() {
        // 1. 通过被封装对象的 readData（读取数据）方法获取数据。
        // 2. 如果数据被压缩就尝试解压。
        // 3. 返回结果。
        return decompress(super.readData());
    }

    /**
     * 压缩
     * @param stringData
     * @return
     */
    private String compress(String stringData){
        byte[] data = stringData.getBytes();
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
            DeflaterOutputStream dos = new DeflaterOutputStream(bout, new Deflater(compLevel));
            dos.write(data);
            dos.close();
            bout.close();
            return Base64.getEncoder().encodeToString(bout.toByteArray());
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 解压
     * @param stringData
     * @return
     */
    private String decompress(String stringData){
        byte[] data = Base64.getDecoder().decode(stringData);
        try {
            InputStream in = new ByteArrayInputStream(data);
            InflaterInputStream iin = new InflaterInputStream(in);
            final ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
            int b;
            while ((b=iin.read())!=-1){
               bout.write(b);
            }
            in.close();
            iin.close();
            bout.close();
            return new String(bout.toByteArray());
        } catch (IOException ex) {
            return null;
        }
    }
}
