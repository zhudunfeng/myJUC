package com.adun.test_jvm.test_nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zhudunfeng
 * @date 2020/11/10 23:25
 */
public class FileChannelDemo {
    public static void main(String[] args) throws Exception {
        //获取文件输入流
        File file = new File("1.txt");

        FileInputStream inputStream = new FileInputStream(file);
        //从文件输入流获取通道
        FileChannel inputStreamChannel = inputStream.getChannel();

        //获取文件输出流
        FileOutputStream outputStream = new FileOutputStream(new File("2.txt"));
        //从文件输出流获取通道
        FileChannel outputStreamChannel = outputStream.getChannel();

        //创建一个byteBuffer，小文件所以就直接一次读取，不分多次循环了
        ByteBuffer byteBuffer = ByteBuffer.allocate((int)file.length());

        //把输入流通道的数据读取到缓冲区
        inputStreamChannel.read(byteBuffer);

        //**切换成读模式**
        byteBuffer.flip();

        //把数据从缓冲区写入到输出流通道
        outputStreamChannel.write(byteBuffer);

        //关闭通道，回收资源
        outputStream.close();
        inputStream.close();
        outputStreamChannel.close();
        inputStreamChannel.close();
    }
}
