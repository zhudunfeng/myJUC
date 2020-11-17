package com.adun.test_demo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author zhudunfeng
 * @date 2020/11/14 22:59
 *
 *
 * ServerSocketChannel参数，也就是option()常用参数：
 *
 * SO_BACKLOG Socket参数，服务端接受连接的队列长度，如果队列已满，客户端连接将被拒绝。默认值，Windows为200，其他为128。
 *
 * SocketChannel参数，也就是childOption()常用的参数：
 *
 * SO_RCVBUF Socket参数，TCP数据接收缓冲区大小。
 * TCP_NODELAY TCP参数，立即发送数据，默认值为Ture。
 * SO_KEEPALIVE Socket参数，连接保活，默认值为False。启用该功能时，TCP会主动探测空闲连接的有效性。
 */
public class MyServer {
    //将异常进行抛出，使用Netty的Handler处理方法进行统一处理
    public static void main(String[] args) throws Exception {
        //创建两个线程数组bootGroup、workerGroup
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //创建服务端的启动对象，设置参数
            ServerBootstrap bootstrap = new ServerBootstrap();
            //设置两个线程数组bossGroup和workerGroup
            bootstrap.group(bossGroup,workerGroup)
                    //设置服务端通道实现类型
                    .channel(NioServerSocketChannel.class)
                    //设置线程队列得到连接个数【Windows默认200，Linux默认128】
                    .option(ChannelOption.SO_BACKLOG,128)
                    //设置保持活动连接状态【连接保活，默认False】
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    //使用匿名内部类的形式初始化通道对象
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //给pipeLine管道设置处理器
                            socketChannel.pipeline().addLast(new MyServerHandler());
                        }
                    });//给workerGroup的EventLoop对应的管道设置处理器
            System.out.println("java技术爱好者的服务端已经准备就绪");

            //绑定端口号,启动服务端
            ChannelFuture channelFuture = bootstrap.bind(6666).sync();
            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();

        }finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }
}
