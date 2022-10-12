package com.adun.test_socket.testTCP;

import org.junit.platform.commons.util.StringUtils;

import java.io.*;
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
public class Server1 {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);
        Socket socket = server.accept();

        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, Charset.defaultCharset());
        BufferedReader reader = new BufferedReader(isr);
//        while(true) {
//            String value = reader.readLine();
//            if (null==value || "".equals(value)){
//                break;
//            }
//            System.out.println(value);
//        }


//        String value=reader.readLine();
//        while (null!=value && !"".equals(value)){
//            System.out.println(value);
//            value = reader.readLine();
//        }

        PrintWriter out = new PrintWriter(socket.getOutputStream());
        out.write("HTTP/1.1 200 OK\r\n");
        out.write("Content-type: text/html;charset=utf-8\r\n");
        out.write("\r\n");
        out.write("<html>");
        out.write("    <body>");
        out.write("        <input type='button' value='请点我' />");
        out.write("    </body>");
        out.write("</html>");
        out.flush();
    }
}
