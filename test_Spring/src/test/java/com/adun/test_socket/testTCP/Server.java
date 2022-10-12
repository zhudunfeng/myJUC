package com.adun.test_socket.testTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author ADun
 * @date 2022/9/2 14:39
 * Transmission Control Protocol 		C/S     B/S
 * 	服务端		ServerSocket	Socket
 * 	客户端		Socket
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);
        Socket socket = server.accept();

        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, Charset.defaultCharset());
        BufferedReader reader = new BufferedReader(isr);

        String value = reader.readLine();
        System.out.println(value);
    }
}
