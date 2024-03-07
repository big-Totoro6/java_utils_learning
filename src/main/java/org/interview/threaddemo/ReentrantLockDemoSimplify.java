package org.interview.threaddemo;

import java.lang.annotation.Target;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemoSimplify {
    private static ReentrantLock lock = new ReentrantLock();
    //需要有一个标志判断该让哪个线程执行
    private static int flag = 0;
    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();

    public static void main(String[] args) {
        new Thread(() -> printLetter(0,conditionA),"A").start();
        new Thread(() -> printLetter(1,conditionB),"B").start();
        new Thread(() -> printLetter(2,conditionC),"C").start();
    }

    public static void printLetter(Integer target, Condition condition){
        for (int i = 0; i < 3; i++) {
            lock.lock();
            try {
                while (flag != target) {
                    condition.await();
                }
                //是我们要的
                System.out.print(Thread.currentThread().getName());
                //让他在 0-1-2-0-1-2 往复
                flag = (flag+1)%3;
                if (flag==0){
                    conditionA.signal();
                }else if (flag==1){
                    conditionB.signal();
                }else {
                    conditionC.signal();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }
}
