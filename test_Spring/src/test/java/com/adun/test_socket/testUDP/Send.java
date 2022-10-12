package com.adun.test_socket.testUDP;

import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;

/**
 * @author ADun
 * @date 2022/9/1 14:51
 */
public class Send {
    public static void main(String[] args) throws IOException {
        String message="阿敦很帅";
        byte[] bytes = message.getBytes(Charset.defaultCharset());

        /*
            1.数据            byte
            2.从哪开始          0
            3.多长            length
            4.接收端的ip        ip
            5.接收端开放的端口号 port
         */
        int begin = 0;
        int length = bytes.length;
        InetAddress ip = InetAddress.getByName("127.0.0.1");
        int port = 9999;
        //构建数据包
        DatagramPacket datagramPacket = new DatagramPacket(bytes, begin, length, ip, port);

        //socket发送
        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.send(datagramPacket);
    }
}
