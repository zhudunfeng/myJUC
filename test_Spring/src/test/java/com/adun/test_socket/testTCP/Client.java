package com.adun.test_socket.testTCP;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author ADun
 * @date 2022/9/2 14:39
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",9999);

        String message = "阿敦很帅";
        OutputStream os = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(os);
        writer.println(message);
        writer.flush();
    }
}
