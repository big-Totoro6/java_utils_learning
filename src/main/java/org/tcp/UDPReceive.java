package org.tcp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * UDP：面向非连接、传输不可靠(容易发生丢包[，导致数据丢失)、用于传输少量数据(数据报包模式)、速度快。
 * 工作模式在发送端和接收端客户端之间进行。
 */
public class UDPReceive {
    public static void main(String[] args) throws Exception {
        // 接收端对象
        DatagramSocket receiver = new DatagramSocket(14000);
        byte[] buffer = new byte[1024];
        // 接收数据
        DatagramPacket dp = new DatagramPacket(buffer, 1024);
        receiver.receive(dp);
        // 打印接收到的数据
        String msg = new String(dp.getData(), 0, dp.getLength());
        System.out.println("接收到的数据：" + msg);
    }

}
