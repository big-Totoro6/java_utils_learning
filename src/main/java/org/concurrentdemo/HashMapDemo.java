package org.concurrentdemo;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapDemo {
    public static void main(String[] args) {
        // 创建一个非线程安全的 HashMap
        HashMap<String, Integer> map = new HashMap<>();

        // 创建并启动多个线程进行并发操作
        Thread[] threads = new Thread[5]; // 减少线程数量
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Worker(map, "Thread" + i));
            threads[i].start();
        }

        // 等待所有线程执行完成
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 打印最终的 HashMap 内容
        System.out.println("Final HashMap: " + map);
    }

    static class Worker implements Runnable {
        private HashMap<String, Integer> map;
        private String name;

        public Worker(HashMap<String, Integer> map, String name) {
            this.map = map;
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) { // 减少每个线程的操作次数
                // 添加或更新键值对
                map.put(name, i);
                System.out.println("Thread " + name + " put: " + i);
                try {
                    Thread.sleep(100); // 模拟执行一段耗时操作
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
