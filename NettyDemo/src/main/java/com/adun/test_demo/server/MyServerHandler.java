package com.adun.test_demo.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author zhudunfeng
 * @date 2020/11/14 23:09
 *
 * 自定义的Handler需要继承Netty规定好的HandlerAdapter
 * 才能被Netty框架所关联，有点类似SpringMVC的适配者模式
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //获取客户端发送过来的消息
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("收到客户端"+ctx.channel().remoteAddress()+"发送的消息："+byteBuf.toString(CharsetUtil.UTF_8));

        /**
         * 如果Handler处理器有一些长时间的业务处理，可以交给taskQueue异步处理。
         */
        //获取到线程池eventLoop,添加线程，执行
//        ctx.channel().eventLoop().execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    //长时间操作，不至于长时间的业务操作导致Handler阻塞
//                    Thread.sleep(1000);
//                    System.out.println("长时间的业务处理");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        /**
         * 延时任务队列和上面介绍的任务队列非常相似，只是多了一个可延迟一定时间再执行的设置
         */
//        ctx.channel().eventLoop().schedule(new Runnable() {
//            @Override
//            public void run() {
//                //长时间操作，不至于超时间的业务操作导致Handler阻塞
//                try {
//                    Thread.sleep(1000);
//                    System.out.println("长时间的业务处理");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        },5, TimeUnit.SECONDS);//5秒后执行

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //发送消息给客户端
        ctx.writeAndFlush(Unpooled.copiedBuffer("服务端已收到消息，并给你发送一个问号？",CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //发生异常，关闭通道
        ctx.close();
    }
}
