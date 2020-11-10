package com.adun.test_jvm.test_nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author zhudunfeng
 * @date 2020/11/10 23:27
 */
public class SocketChannelDemo {
    public static void main(String[] args) throws Exception {
        //获取ServerSocketChannel：监听
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //绑定地址，端口号
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6666);
        serverSocketChannel.bind(address);

        //创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (true) {
            //获取SocketChannel
            SocketChannel socketChannel = serverSocketChannel.accept();
            while (socketChannel.read(byteBuffer) != -1){
                //打印结果
                System.out.println(new String(byteBuffer.array()));
                //清空缓冲区
                byteBuffer.clear();
            }
        }
    }
}
