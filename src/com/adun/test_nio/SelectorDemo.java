package com.adun.test_nio;

import org.junit.Test;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/**
 * @author zhudunfeng
 * @date 2020/11/11 19:01
 * Selector翻译成选择器，有些人也会翻译成多路复用器，实际上指的是同一样东西。
 *
 * ###只有网络IO才会使用选择器，文件IO是不需要使用的。####
 *
 * 选择器可以说是NIO的核心组件，它可以监听通道的状态，来实现异步非阻塞的IO。
 * 换句话说，也就是事件驱动。以此实现单线程管理多个Channel的目的。
 *
 * 2.3.1 核心API
 * API方法名	        作用
 * Selector.open()	打开一个选择器。
 * select()	        选择一组键，其相应的通道已为 I/O 操作准备就绪。
 * selectedKeys()	返回此选择器的已选择键集。
 */
public class SelectorDemo {
    public static void main(String[] args) {

    }
    /**
     * transferTo()：把源通道的数据传输到目的通道中。
     * transferFrom()：把来自源通道的数据传输到目的通道。
     * 这组方法的方向是相反的
     */
    @Test
    public void transferTo() throws IOException {
        long startTime = System.currentTimeMillis();
        //获取文件输入流
        File file = new File("1.txt");
        FileInputStream inputStream = new FileInputStream(file);
        //从文件输入流获取通道
        FileChannel inputStreamChannel = inputStream.getChannel();

        //获取文件输出流
        FileOutputStream outputStream = new FileOutputStream(new File("3.txt"));
        //从文件输出流获取通道
        FileChannel outputStreamChannel = outputStream.getChannel();

        //channel必须依赖buffer
        //创建一个byteBuffer，小文件所以就直接一次读取，不分多次循环了
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        //把输入流通道的数据读取到输出流的通道
        inputStreamChannel.transferTo(0, byteBuffer.limit(), outputStreamChannel);

        //关闭通道，回收资源
        outputStream.close();
        inputStream.close();
        outputStreamChannel.close();
        inputStreamChannel.close();
        long endTime = System.currentTimeMillis();
        double gloal=(endTime-startTime)/1000.0;
        System.out.println(gloal);//0.005
    }

    @Test
    public void transferFrom() throws IOException {
        //获取文件输入流
        File file = new File("1.txt");
        FileInputStream inputStream = new FileInputStream(file);
        //从文件输入流获取通道
        FileChannel inputStreamChannel = inputStream.getChannel();

        //获取文件输出流
        FileOutputStream outputStream = new FileOutputStream(new File("3.txt"));
        //从文件输出流获取通道
        FileChannel outputStreamChannel = outputStream.getChannel();

        //channel必须依赖buffer
        //创建一个byteBuffer，小文件所以就直接一次读取，不分多次循环了
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        //把输入流通道的数据读取到输出流的通道
//        inputStreamChannel.transferTo(0, byteBuffer.limit(), outputStreamChannel);
        outputStreamChannel.transferFrom(inputStreamChannel, 0, byteBuffer.limit());

        //关闭通道，回收资源
        outputStream.close();
        inputStream.close();
        outputStreamChannel.close();
        inputStreamChannel.close();
    }

    /**
     *   分散读取和聚合写入
     *   GatheringByteChannel, ScatteringByteChannel接口
     */
    @Test
    public void gsIo() throws IOException {
        long startTime = System.currentTimeMillis();
        //创建文件输入流
        FileInputStream inputStream = new FileInputStream(new File("1.txt"));
        //从文件输入流获取通道
        FileChannel inputStreamChannel = inputStream.getChannel();

        //创建文件输出流
        FileOutputStream outputStream = new FileOutputStream(new File("4.txt"));
        //从文件输入流获取通道
        FileChannel outputStreamChannel = outputStream.getChannel();

        //创建三个缓冲区，分别是5
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(5);
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(5);
        ByteBuffer byteBuffer3 = ByteBuffer.allocate(5);
        //创建一个缓冲区数组
        ByteBuffer[] buffers = {byteBuffer1, byteBuffer2, byteBuffer3};

        //循环写入到buffers缓冲区数组中，分散读取
        long read;
        long sumLength=0;
        while ((read=inputStreamChannel.read(buffers))!=-1){
            sumLength+=read;
            Arrays.stream(buffers)
                    .map(buffer->"posstion="+buffer.position()+",limit="+buffer.limit())
                    .forEach(System.out::println);
            //切换模式
            Arrays.stream(buffers).forEach(Buffer::flip);

            //聚合写入文件输出通道
            outputStreamChannel.write(buffers);

            //清空缓冲区
            Arrays.stream(buffers).forEach(Buffer::clear);
        }

        System.out.println("总长度："+sumLength);

        //关闭通道
        outputStream.close();
        inputStream.close();
        outputStreamChannel.close();
        inputStreamChannel.close();
        long endTime = System.currentTimeMillis();
        double gloalTime=(endTime-startTime)/1000.0;
        System.out.println(gloalTime);//0.045

        //posstion=5,limit=5
        //posstion=5,limit=5
        //posstion=5,limit=5
        //posstion=5,limit=5
        //posstion=5,limit=5
        //posstion=1,limit=5
        //总长度：26

        // 可以看到循环了两次。第一次循环时，三个缓冲区都读取了5个字节，总共读取了15，也就是读满了。还剩下11个字节，于是第二次循环时，前两个缓冲区分配了5个字节，最后一个缓冲区给他分配了1个字节，刚好读完。总共就是26个字节。
        //
        //这就是分散读取，聚合写入的过程。
        //
        //使用场景就是可以使用一个缓冲区数组，自动地根据需要去分配缓冲区的大小。可以减少内存消耗。网络IO也可以使用，这里就不写例子演示了。


    }

    /**
     *  非直接/直接缓冲区
     */
    @Test
    public void  allocateDirect() throws IOException {
        //记录开始时间
        long startTime = System.currentTimeMillis();

        //获取文件输入流
        File file = new File("D:\\PrProject\\序列 02.mp4");
        String name=file.getName().split("[.]")[0];
        //String[] split = name.split("[.]");
        //String fileName=name.substring(0,name.lastIndexOf("."));
        FileInputStream inputStream = new FileInputStream(file);
        //从文件输入流获取通道
        FileChannel inputStreamChannel = inputStream.getChannel();

        //获取文件输出流
        FileOutputStream outputStream = new FileOutputStream(new File("E:\\"+name+".mp4"));
        //从文件输出流获取通道
        FileChannel outputStreamChannel = outputStream.getChannel();

        //创建一个直接缓冲区5m   |消耗时间：31毫秒
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(5 * 1024 * 1024);
        //创建一个非直接缓冲区    |消耗时间：36毫秒
//        ByteBuffer byteBuffer = ByteBuffer.allocate(5 * 1024 * 1024);

        //写入缓冲区
        while (inputStreamChannel.read(byteBuffer)!=-1){
            //切换读模式
            byteBuffer.flip();
            outputStreamChannel.write(byteBuffer);
            //清空缓冲区
            byteBuffer.clear();
        }

        //关闭通道，回收资源
        outputStream.close();
        inputStream.close();
        outputStreamChannel.close();
        inputStreamChannel.close();

        //记录结束时间
        long endTime = System.currentTimeMillis();
        System.out.println("消耗时间："+(endTime-startTime)+"毫秒");

    }




}
