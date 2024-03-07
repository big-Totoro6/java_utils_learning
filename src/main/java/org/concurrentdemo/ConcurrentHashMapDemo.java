package org.concurrentdemo;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        // 创建一个 ConcurrentHashMap
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();

        // 创建并启动多个线程进行并发操作
        MyRunnable runnable1 = new MyRunnable(concurrentHashMap, "Thread1");
        MyRunnable runnable2 = new MyRunnable(concurrentHashMap, "Thread21");
        MyRunnable runnable3 = new MyRunnable(concurrentHashMap, "Thread311");

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        Thread thread3 = new Thread(runnable3);

        thread1.start();
        thread2.start();
        thread3.start();

        // 等待所有线程执行完成
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 打印最终的 ConcurrentHashMap 内容
        System.out.println("Final ConcurrentHashMap: " + concurrentHashMap);
    }

    static class MyRunnable implements Runnable {
        private ConcurrentHashMap<String, Integer> map;
        private String name;

        public MyRunnable(ConcurrentHashMap<String, Integer> map, String name) {
            this.map = map;
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                // 将当前线程名作为键，值为当前线程名的长度
                map.put(name, name.length());
                System.out.println("Thread " + name + " put: " + name + ", " + name.length());
                try {
                    Thread.sleep(100); // 模拟执行一段耗时操作
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}