package com.adun.test_nio;

import java.nio.ByteBuffer;

/**
 * @author zhudunfeng
 * @date 2020/11/10 23:24
 *  https://mp.weixin.qq.com/s/GfV9w2B0mbT7PmeBS45xLw?spm=a2c6h.12873639.0.0.6dd14a61HjGXZt
 *
 *
 * <p>
 * NIO的核心	    对应的类或接口	    应用	            作用
 * 缓冲区	    Buffer	            文件IO/网络IO	存储数据
 * 通道	        Channel	            文件IO/网络IO	运输
 * 选择器	    Selector	        网络IO	        控制器
 */
public class NIODemo {
    public static void main(String[] args) {
        //主要分成两种：JVM堆内内存块Buffer、堆外内存块Buffer。

        //创建堆内内存块(非直接缓冲区)的方法是：
        //创建堆内内存块HeapByteBuffer
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(1024);

        String msg = "java技术爱好者";
        //包装一个byte[]数组获得一个Buffer，实际类型是HeapByteBuffer
        ByteBuffer byteBuffer2 = ByteBuffer.wrap(msg.getBytes());

        /**
         * ==============================================
         */

        //创建堆外内存块(直接缓冲区)的方法：
        //创建堆外内存块DirectByteBuffer
        ByteBuffer byteBuffer3 = ByteBuffer.allocateDirect(1024);
    }
}
