package com.adun.test_demo.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;
import lombok.Data;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhudunfeng
 * @date 2020/11/15 22:12
 *
 * 处理器Handler主要分为两种：
 *
 * ChannelInboundHandlerAdapter(入站处理器)、ChannelOutboundHandler(出站处理器)
 *
 * **入站指的是数据从底层java NIO Channel到Netty的Channel。
 *
 * **出站指的是通过Netty的Channel来操作底层的java NIO Channel。
 *
 * #### ChannelInboundHandlerAdapter处理器常用的事件有：
 * 注册事件 fireChannelRegistered。
 * 连接建立事件 fireChannelActive。
 * 读事件和读完成事件 fireChannelRead、fireChannelReadComplete。
 * 异常通知事件 fireExceptionCaught。
 * 用户自定义事件 fireUserEventTriggered。
 * Channel 可写状态变化事件 fireChannelWritabilityChanged。
 * 连接关闭事件 fireChannelInactive。
 *
 * #### ChannelOutboundHandler处理器常用的事件有：
 * 端口绑定 bind。
 * 连接服务端 connect。
 * 写事件 write。
 * 刷新时间 flush。
 * 读事件 read。
 * 主动断开连接 disconnect。
 * 关闭 channel 事件 close。
 * 还有一个类似的handler()，主要用于装配parent通道，也就是bossGroup线程。一般情况下，都用不上这个方法。
 */
public class MyClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //发送消息到服务端
        ctx.writeAndFlush(Unpooled.copiedBuffer("歪比巴卜~茉莉~Are you good~马来西亚~", CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //接收服务端发送过来的消息
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("收到服务器"+ctx.channel().remoteAddress()+"的消息："+byteBuf.toString(CharsetUtil.UTF_8));
//        ChannelConfig config = ctx.channel().config();//获取配置参数
//        //获取ChannelOption.SO_BACKLOG参数,
//        Integer soBackLogConfig = config.getOption(ChannelOption.SO_BACKLOG);
//        //因为我启动器配置的是128，所以我这里获取的soBackLogConfig=128
//        System.out.println(soBackLogConfig);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //发生异常，关闭通道
        System.out.println(cause);
        ctx.close();
    }
}
