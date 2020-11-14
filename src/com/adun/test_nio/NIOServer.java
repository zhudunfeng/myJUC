package com.adun.test_nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zhudunfeng
 * @date 2020/11/11 22:59
 *
 * 其实NIO的主要用途是网络IO，在NIO之前java要使用网络编程就只有用Socket。而Socket是阻塞的，显然对于高并发的场景是不适用的。所以NIO的出现就是解决了这个痛点。
 *
 * 主要思想是把Channel通道注册到Selector中，通过Selector去监听Channel中的事件状态，这样就不需要阻塞等待客户端的连接，从主动等待客户端的连接，变成了通过事件驱动。没有监听的事件，服务器可以做自己的事情。
 *
 * * ServerSockectChannel，监听新进来的TCP连接，像Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel。
 *  * DatagramChannel，通过UDP读写网络中的数据。
 */
public class NIOServer {

    public static void main(String[] args) throws IOException {
        //打开一个ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //绑定地址
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6666);
        serverSocketChannel.bind(address);

        //设置ServerSockectChannel为非阻塞,默认为阻塞
        serverSocketChannel.configureBlocking(false);

        //打开一个选择器
        Selector selector = Selector.open();
        //serverSocketChannel注册到选择器中，监听连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //循环等待客户端连接
        while (true){
            //等待3秒（返回0相当于没有事件）如果没有事件，则跳过
            if(selector.select(3000)==0){
                System.out.println("服务器等待3秒，没有连接");
                continue;
            }

            //如果有事件selector.select(3000)>0的情况，获取事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //获取迭代器遍历
            Iterator<SelectionKey> it = selectionKeys.iterator();
            while (it.hasNext()){
                //获取到事件
                SelectionKey selectionKey = it.next();
                //判断如果是连接事件
                if (selectionKey.isAcceptable()) {
                    //服务器与客户端建立连接，获取socketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //设置成非阻塞
                    socketChannel.configureBlocking(false);
                    //把socketChannel注册到selector中，监听读事件，并绑定一个缓冲区
                    socketChannel.register(selector,  SelectionKey.OP_READ,ByteBuffer.allocate(1024));


                }
                //判断如果是读事件
                if(selectionKey.isReadable()){
                    //获取通道
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    //获取关联的byteBuffer
                    ByteBuffer byteBuffer= (ByteBuffer) selectionKey.attachment();
                    //打印从客户端获取到的数据
                    socketChannel.read(byteBuffer);
                    System.out.println("from客户端："+new String(byteBuffer.array()));

                }

                //从事件集合中删除已处理的事件，防止重复处理
                it.remove();
            }
        }

    }
}
