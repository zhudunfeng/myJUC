package com.adun.test_jvm.test_nio;

import javafx.util.Builder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zhudunfeng
 * @date 2020/11/12 10:27
 */
public class GroupChatServer {
    //选择器
    private Selector selector;

    //ServerSocketChannel通道
    private ServerSocketChannel serverSocketChannel;

    //端口号
    public static final int PORT=6667;

    //构造器初始化成员变量，【类加载+实例初始化（调用哪个构造器）】
    public GroupChatServer() {
        try {
            //打开一个选择器
            this.selector = Selector.open();
            //打开serverSocketChannel
            this.serverSocketChannel=ServerSocketChannel.open();
            //绑定地址，端口号
            this.serverSocketChannel.bind(new InetSocketAddress("127.0.0.1",PORT));
            //设置为非阻塞
            serverSocketChannel.configureBlocking(false);
            //serverSocketChannel注册到选择器中,监听连接事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 监听，并且接受客户端消息，转发到其他客户端
     */
    public void listen(){
        try {
            while (true){
                //获取监听的事件总数
                int count = selector.select(2000);
                if(count>0){
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    //获取SelectionKey集合
                    Iterator<SelectionKey> it = selectionKeys.iterator();
                    while (it.hasNext()){
                        SelectionKey key = it.next();
                        //如果是获取连接事件
                        if(key.isAcceptable()){
                            //服务器与客户端建立连接，获取socketChannel
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            //设置为非阻塞
                            socketChannel.configureBlocking(false);
                            //把socketChannel注册到selector中，监听读事件
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            System.out.println(socketChannel.getRemoteAddress()+ "上线了~");
                        }

                        //如果是读就绪事件
                        if(key.isReadable()){
                            //读取消息，并且转发到其它客户端
                            readData(key);
                        }

                        //从事件集合中删除已处理的事件，防止重复处理
                        it.remove();
                    }
                }else{
                    System.out.println("等待...");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //获取客户端发送过来的消息
    private void readData(SelectionKey selectionKey){
        SocketChannel socketChannel=null;
        try {
            //从selectionKey中获取channel
            socketChannel = (SocketChannel) selectionKey.channel();
            //创建一个缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            //把通道的数据写入到缓冲区中
            int count = socketChannel.read(byteBuffer);
            //判断返回的count是否大于0，大于0表示读取到了数据
            if(count>0){
                //把缓冲区的byte[]转成字符串
                String msg = new String(byteBuffer.array());
                //输出该消息到控制台
                System.out.println("from客户端："+msg);
                //转发到其他客户端
                notifyAllClient(msg,socketChannel);
            }

        } catch (Exception e) {
            try {
                //打印离线的通知
                System.out.println(socketChannel.getRemoteAddress()+"离线了...");
                //取消注册
                selectionKey.cancel();
                //关闭流
                socketChannel.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    /**
     * 转发消息到其他客户端
     * @param msg 消息
     * @param noNotifyChannel 不需要通知的Channel
     */
    private void notifyAllClient(String msg,SocketChannel noNotifyChannel) throws IOException {
        System.out.println("服务器转发消息~");
        for (SelectionKey selectionKey : selector.keys()) {
            Channel channel = selectionKey.channel();
            //channel的类型实际类型是SocketChannel,并且排除不需要通知的通道
            if(channel instanceof SocketChannel && channel != noNotifyChannel){
                //强制转化成SocketChannel类型
                SocketChannel socketChannel = (SocketChannel) channel;
                //通过消息，包裹获取一个缓冲区
                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
                socketChannel.write(byteBuffer);
            }
        }
    }


    public static void main(String[] args) {
        GroupChatServer chatServer = new GroupChatServer();
        //启动服务器，监听
        chatServer.listen();
//        Builder<GroupChatServer> aNew = GroupChatServer::new;
//        aNew.build().listen();

    }
}
