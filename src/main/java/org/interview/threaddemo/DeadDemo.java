package org.interview.threaddemo;

public class DeadDemo {
    private static Object lockA = new Object();
    private static Object lockB = new Object();
    /**
     * 死锁四大特性
     * 互斥条件（Mutual Exclusion）：至少有一个资源是被独占的，即一次只能被一个进程或线程使用。如果另一个进程或线程希望使用此资源，它必须等待该资源被释放。
     *
     * 占有和等待（Hold and Wait）：进程或线程至少占有一个资源并且正在等待获取另一个资源，而这个另一个资源正被其他进程或线程所持有。
     *
     * 不可抢占（No Preemption）：资源不能被强行从一个进程或线程中抢占，只能在持有资源的进程或线程显式释放后才能被其他进程或线程获取。
     *
     * 循环等待（Circular Wait）：存在一个进程或线程链，每个进程或线程都在等待下一个进程或线程所持有的资源。
     */
    public static void main(String[] args) {
        // 创建两个资源对象
        final Object resource1 = new Object();
        final Object resource2 = new Object();

        // 线程1尝试获取资源1
        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: Holding resource 1...");
                try {
                    Thread.sleep(100); // 为了让线程2有机会获得资源2
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 1: Waiting for resource 2...");
                synchronized (resource2) {
                    System.out.println("Thread 1: Holding resource 1 and resource 2...");
                }
            }
        });

        // 线程2尝试获取资源2
        Thread thread2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread 2: Holding resource 2...");
                try {
                    Thread.sleep(100); // 为了让线程1有机会获得资源1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 2: Waiting for resource 1...");
                synchronized (resource1) {
                    System.out.println("Thread 2: Holding resource 1 and resource 2...");
                }
            }
        });

        // 启动线程1和线程2
        thread1.start();
        thread2.start();
    }

}
