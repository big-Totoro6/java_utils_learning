package org.tcp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSend {
    public static void main(String[] args) throws Exception {
        String data = "我是udp的发送端";
        // 创建发送端,端口为13000
        DatagramSocket sender = new DatagramSocket(13000);
        // 发送数据
        DatagramPacket dp = new DatagramPacket(
                data.getBytes(), // 发送的数据
                data.getBytes().length,  // 发送的数据的长度
                InetAddress.getLocalHost(), // 接收端的ip
                14000); // 接收端的端口

        sender.send(dp);
        sender.close();

    }
}
