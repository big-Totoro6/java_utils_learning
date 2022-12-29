package org.tcp;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP ：面向连接(经历三次握手)、传输可靠(保证数据正确性,保证数据顺序)、
 * 用于传输大量数据(流模式)、速度慢，建立连接需要开销较多(时间，系统资源)。
 *
 * 工作模式在 服务端和客户端之间进行。
 */
public class TCPServer {
    public static void main(String[] args) throws Exception {
        String data = "我是tcp的服务端";

        // 创建TCP服务端，指定端口为8888
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务端已就绪！");
        // 允许客户端建立连接
        boolean accept = true;
        while (accept) {
            Socket client = serverSocket.accept();
            System.out.println("客户端 ：" + client.getInetAddress() + " 已连接。");
            // 通过客户端的输出流，给客户端输出数据
            PrintStream print = new PrintStream(client.getOutputStream());
            print.println(data);
            print.close();
        }
        serverSocket.close();
    }
}
