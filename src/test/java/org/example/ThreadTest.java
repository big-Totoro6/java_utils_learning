package org.example;

import org.junit.Test;

import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {
    /**
     * 定制线程池
     corePoolSize the number of threads to keep in the pool, even
     if they are idle, unless {@code allowCoreThreadTimeOut} is set
     * param corePoolSize the number of threads to keep in the pool, even
     *        if they are idle, unless {@code allowCoreThreadTimeOut} is set
     * param maximumPoolSize the maximum number of threads to allow in the
     *        pool
     * param keepAliveTime when the number of threads is greater than
     *        the core, this is the maximum time that excess idle threads
     *        will wait for new tasks before terminating.
     * param unit the time unit for the {@code keepAliveTime} argument
     * param workQueue the queue to use for holding tasks before they are
     *        executed.  This queue will hold only the {@code Runnable}
     *        tasks submitted by the {@code execute} method.
     * param threadFactory the factory to use when the executor
     *        creates a new thread
     * param handler the handler to use when execution is blocked
     *        because the thread bounds and queue capacities are reached
     *
     * corePoolSize    核心线程大小
     * maximumPoolSize 最大线程池容量
     * keepAliveTime   线程空闲时 存活时间
     * unit            时间单位
     * workQueue       工作队列
     * threadFactory   线程工厂
     * handler         执行拒绝策略的对象
     *
     * tip:
     * 使用容量大的队列 和小线程数 ：可以减少线程上下文切换  IO不密集使用
     * 使用容量小的队列 和大线程数 ：线程上下文切换变多，但效率更高   IO密集使用
     */
    @Test
    public void test_threadPoolExecutor(){
        //常见的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(1);// LinkedBlockingQueue<Runnable> 无界队列 会OOM
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();// LinkedBlockingQueue<Runnable> 无界队列 会OOM
        ExecutorService executorService1 = Executors.newCachedThreadPool();// SynchronousQueue<Runnable>
        Executors.newScheduledThreadPool(1);// new DelayedWorkQueue() 延时队列
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();//DelayedWorkQueue 延时队列
    }

    static class MyFactory implements ThreadFactory{

        /**
         * Constructs a new {@code Thread}.  Implementations may also initialize
         * priority, name, daemon status, {@code ThreadGroup}, etc.
         *
         * @param r a runnable to be executed by new thread instance
         * @return constructed thread, or {@code null} if the request to
         * create a thread is rejected
         */
        @Override
        public Thread newThread(java.lang.Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("Totoro Factory");
            //exception handle
            thread.setUncaughtExceptionHandler((t,e)->{
                System.out.println(t.getName()+ "--------"+e.getMessage());
            });
            return thread;
        }
    }

    static class MyRejectionHandler implements RejectedExecutionHandler{
        /**
         * Method that may be invoked by a {@link ThreadPoolExecutor} when
         * {@link ThreadPoolExecutor#execute execute} cannot accept a
         * task.  This may occur when no more threads or queue slots are
         * available because their bounds would be exceeded, or upon
         * shutdown of the Executor.
         *
         * <p>In the absence of other alternatives, the method may throw
         * an unchecked {@link RejectedExecutionException}, which will be
         * propagated to the caller of {@code execute}.
         *
         * @param r        the runnable task requested to be executed
         * @param executor the executor attempting to execute this task
         * @throws RejectedExecutionException if there is no remedy
         */
        @Override
        public void rejectedExecution(java.lang.Runnable r, ThreadPoolExecutor executor) {
            //打印日志 暂存任务 重新执行等拒绝策略
            System.out.println("The Mission is Rejected");
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1,
                1,
                0L,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1),//容量有限 可能会OOM
                new MyFactory(),
                new MyRejectionHandler()
        );

        threadPoolExecutor.execute(()->{
            try{
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("咱自个提的报错");
        });
        threadPoolExecutor.execute(()->{
            System.out.println("第二个 打印的线程");
        });
        //因为最大线程数量1   队列容量1    这第三个线程执行就会超过
        threadPoolExecutor.execute(()->{
            System.out.println("超过队列大小的任务");
        });
        threadPoolExecutor.shutdown();
    }
}
