package com.adun.test_socket.testUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.Charset;

/**
 * @author ADun
 * @date 2022/9/1 14:51
 *
 * UDP （User Datagram Protocol）
 * 发送端   Java         DatagramSocket      DatagramPacket    byte[]
 * 接收端   Java         DatagramSocket      DatagramPacket    byte[]
 */
public class Receive {
    public static void main(String[] args) throws IOException {
        //构建一个空的数组， 装数据
        byte[] bytes = new byte[12];

        //构建一个空的数据包
        DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length);

        //创建连接 需要指定一个开发的端口
        DatagramSocket datagramSocket = new DatagramSocket(9999);
        datagramSocket.receive(datagramPacket);

        System.out.println(new String(bytes, Charset.defaultCharset()));
    }
}
