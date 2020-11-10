package com.adun.test_jvm.test_nio;

import java.nio.ByteBuffer;

/**
 * @author zhudunfeng
 * @date 2020/11/10 23:24
 */
public class ByteBufferDemo {
    public static void main(String[] args) throws Exception {
        String msg = "java技术爱好者，起飞！";
        //创建一个固定大小的buffer(返回的是HeapByteBuffer)
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byte[] bytes = msg.getBytes();

        //写入数据到Buffer中
        byteBuffer.put(bytes);

        //**切换成读模式，关键一步**
        byteBuffer.flip();

        //创建一个临时数组，用于存储获取到的数据
        byte[] tempByte = new byte[bytes.length];
        int i = 0;
        //如果还有数据，就循环。循环判断条件
        while (byteBuffer.hasRemaining()) {
            //获取byteBuffer中的数据
            byte b = byteBuffer.get();
            //放到临时数组中
            tempByte[i] = b;
            i++;
        }
        //打印结果
        System.out.println(new String(tempByte));//java技术爱好者，起飞！
    }
}
