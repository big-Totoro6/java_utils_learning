package org.aboutstatic;

public class Demo {
    //想让三个线程轮流打印ABCABCABC 也就是说咱要用锁了
    //这是三个线程争抢的锁
    private static Object lock = new Object();
    //需要有一个标志判断该让哪个线程执行
    private static int flag = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                synchronized (lock) {
                    //你获取了锁 如果不应该你执行 就休眠
                    while (flag != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //让线程B能识别执行
                    flag = 1;
                    System.out.print(Thread.currentThread().getName());
                    //唤醒等待这个锁的其他线程去竞争
                    lock.notifyAll();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                synchronized (lock) {
                    //你获取了锁 如果不应该你执行 就休眠
                    while (flag != 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //让线程B能识别执行
                    flag = 2;
                    System.out.print(Thread.currentThread().getName());
                    //唤醒等待这个锁的其他线程去竞争
                    lock.notifyAll();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                synchronized (lock) {
                    //你获取了锁 如果不应该你执行 就休眠
                    while (flag != 2) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //让线程B能识别执行
                    flag = 0;
                    System.out.print(Thread.currentThread().getName());
                    //唤醒等待这个锁的其他线程去竞争
                    lock.notifyAll();
                }
            }
        }, "C").start();
    }
}
