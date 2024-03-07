package org.interview.threaddemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private static ReentrantLock lock = new ReentrantLock();
    //需要有一个标志判断该让哪个线程执行
    private static int flag = 0;
    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                lock.lock();
                try {
                    while (flag != 0) {
                        //如果不是0 也就是不该线程A执行代码，就让conditionA挂起，这时候，线程就与这个Conition绑定了
                        //之后别人唤醒A线程 直接signal A就好了
                        conditionA.await();
                    }
                    flag = 1;
                    conditionB.signal();
                    System.out.print(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                lock.lock();
                try {
                    while (flag != 1) {
                        conditionB.await();
                    }
                    flag = 2;
                    conditionC.signal();
                    System.out.print(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                lock.lock();
                try {
                    while (flag != 2) {
                        conditionC.await();
                    }
                    flag = 0;
                    System.out.print(Thread.currentThread().getName());
                    conditionA.signal();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        }, "C").start();
    }
}
