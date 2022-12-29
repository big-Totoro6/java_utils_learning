package org.tcp;

import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        // 创建客户端，与服务端建立连接
        Socket client = new Socket("localhost", 8888);
        // 输出服务端输入的数据
        Scanner scanner = new Scanner(client.getInputStream());
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }
        scanner.close();
        client.close();
    }
}
