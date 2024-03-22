package org.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用ThreadLocal保证线程安全
 */
public class DateFormatThreadLocalTest {
    /**
     * 使用ThreadLocal为每个线程创建一个新的 SimpleDateFormat 实例 保证线程安全
     * 使用场景：
     * 数据库连接 或者会话管理，大家都可以共享相同的连接
     * 全局储存的信息：前后端分离项目里面 维护用户的上下文信息或者配置
     *
     * 解析：
     * Thread线程类里面有ThreadLocal.ThreadLocalMap threadLocals = null; 意味着每个线程有自己的ThreadLocalMap
     *
     * Create the map associated with a ThreadLocal. Overridden in InheritableThreadLocal.
     * Params:
     * t – the current thread firstValue – value for the initial entry of the map
     *  t.threadLocals = new ThreadLocalMap(this, firstValue);
     *
     *  并发场景下，每个线程都会存储当前变量副本到自己的ThreadLocalMap中，后续这个线程对这个共享变量的操作都是从ThreaLocalMap里进行变更
     *  不会影响共享变量的值
     *
     *  高并发场景下ThreadLocal会造成内存泄露？为啥？
     *  首先ThreadLocalMap他的entry继承了一个弱引用
     *   static class Entry extends WeakReference<ThreadLocal<?>>
     *       Entry(ThreadLocal<?> k, Object v) {
     *                 super(k);
     *                 value = v;
     *             }
     *  里面用的是弱引用的构造方法
     *  WeakReference，弱引用，当对象仅仅被弱引用指向时，GC会把它回收掉（不论当前空间是否足够）
     *  那ThreadLocal对象是弱引用，外面没有强引用指向的话，GC之后，key就为null了，因为key是弱引用赋值的，value是强引用，value就回收不了，
     *  这个时候内存泄露就出现了，本质原因是value成为了一个永远无法被访问也无法被回收的对象
     *
     *  如何避免OOM：
     *  使用完ThreadLocal后 手动调用remove方法清楚数据
     *  ThreadLocal变量尽量定义成static final类行，避免频繁创建ThreadLocal实例，保证程序中一直存在ThreadLocal强引用
     */
    private static final ThreadLocal<SimpleDateFormat> threadLocalDateFormat = ThreadLocal.withInitial(
            () -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    public static Date parse(String dateString) {
        Date date = null;
        try {
            date = threadLocalDateFormat.get().parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            executorService.execute(() -> {
                System.out.println(parse("2024-03-22 21:37:30"));
            });
        }
        executorService.shutdown();
    }
}
