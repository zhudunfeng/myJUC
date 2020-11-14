package com.adun.test_nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author zhudunfeng
 * @date 2020/11/10 23:27
 *
 * 通过上面的例子可以知道，通过ServerSocketChannel.open()方法可以获取服务器的通道，然后绑定一个地址端口号，接着accept()方法可获得一个SocketChannel通道，也就是客户端的连接通道。
 *
 * 最后配合使用Buffer进行读写即可。
 *
 * 这就是一个简单的例子，实际上上面的例子是阻塞式的。要做到非阻塞还需要使用选择器Selector。
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
