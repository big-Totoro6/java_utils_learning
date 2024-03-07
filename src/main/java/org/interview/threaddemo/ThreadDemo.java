package org.interview.threaddemo;

public class ThreadDemo {
    private static Object lock = new Object();
    //需要有一个标志判断该让哪个线程执行
    private static int flag = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                synchronized (lock) {
                    while (flag != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    flag = 1;
                    System.out.print(Thread.currentThread().getName());
                    lock.notifyAll();
                }

            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                synchronized (lock) {
                    while (flag != 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    flag = 2;
                    System.out.print(Thread.currentThread().getName());
                    lock.notifyAll();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                synchronized (lock) {
                    while (flag != 2) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    flag = 0;
                    System.out.print(Thread.currentThread().getName());
                    lock.notifyAll();
                }
            }
        }, "C").start();

    }
}
