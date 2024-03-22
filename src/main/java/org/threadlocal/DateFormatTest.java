package org.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DateFormatTest {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date parse(String dateString) {
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Exception in thread "pool-1-thread-16" java.lang.NumberFormatException: For input string: "222.E2222E2"
     * 这些异常可能是由于多个线程同时访问了同一个 SimpleDateFormat 实例导致的。SimpleDateFormat 是非线程安全的，
     * 如果多个线程同时访问它，可能会导致解析错误或异常。
     *
     * 多个线程同时调用 parse 方法来解析日期字符串，而 SimpleDateFormat 实例是静态的，被所有线程共享，因此会导致竞争条件和线程安全问题。
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            executorService.execute(()->{
                System.out.println(parse("2024-03-22 21:37:30"));
            });
        }
        executorService.shutdown();
    }
}
