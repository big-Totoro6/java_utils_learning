package org.example;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JobTest {

    @Test
    public void test_timer() throws InterruptedException {
        // 示例代码：
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("当前时间: " + new Date() + "n" +
                        "线程名称: " + Thread.currentThread().getName());
            }
        };
        System.out.println("当前时间: " + new Date() + "n" +
                "线程名称: " + Thread.currentThread().getName());
        Timer timer = new Timer("Timer");
        long delay = 1000L;
        timer.schedule(task, delay);
        //避免定时还没执行 主线程直接结束
        Thread.sleep(2000L);

//输出：
//        当前时间: Fri May 28 15:18:47 CST 2021n线程名称: main
//        当前时间: Fri May 28 15:18:48 CST 2021n线程名称: Timer

    }

    @Test
    public void test_scheduleThreadPoolExecutor() throws InterruptedException {
        // 示例代码：
        TimerTask repeatedTask = new TimerTask() {
            @SneakyThrows
            public void run() {
                System.out.println("当前时间: " + new Date() + "n" +
                        "线程名称: " + Thread.currentThread().getName());
            }
        };
        System.out.println("当前时间: " + new Date() + "n" +
                "线程名称: " + Thread.currentThread().getName());
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
        long delay  = 1000L;
        long period = 1000L;
        executor.scheduleAtFixedRate(repeatedTask, delay, period, TimeUnit.MILLISECONDS);
        Thread.sleep(delay + period * 5);
        executor.shutdown();
//输出：
//        当前时间: Fri May 28 15:40:46 CST 2021n线程名称: main
//        当前时间: Fri May 28 15:40:47 CST 2021n线程名称: pool-1-thread-1
//        当前时间: Fri May 28 15:40:48 CST 2021n线程名称: pool-1-thread-1
//        当前时间: Fri May 28 15:40:49 CST 2021n线程名称: pool-1-thread-2
//        当前时间: Fri May 28 15:40:50 CST 2021n线程名称: pool-1-thread-2
//        当前时间: Fri May 28 15:40:51 CST 2021n线程名称: pool-1-thread-2
//        当前时间: Fri May 28 15:40:52 CST 2021n线程名称: pool-1-thread-2

    }
}
