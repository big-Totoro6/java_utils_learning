package org.example;

//import com.sun.org.slf4j.internal.Logger;
import org.junit.Test;
import org.unsafa.A;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeTest {
    /**因为unsafe涉及到的操作过于底层 所以基本是不允许你直接调用的 Unsafa类里面有public static Unsafe getUnsafe()
     * 会对你的类加载器进行检查，不是Bootstrap classLoader加载的就会抛出安全异常
     * 也就是说，只有启动类加载器加载的类才能够调用 Unsafe 类中的方法，来防止这些方法在不可信的代码中被调用。
     *
     * 利用反射获得 Unsafe 类中已经实例化完成的单例对象 theUnsafe.
     * @return
     */
    private static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
//            new Logger("Jason").error(e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * 分析一下运行结果，首先使用allocateMemory方法申请 4 字节长度的内存空间，调用setMemory方法向每个字节写入内容为byte类型的 1，
     * 当使用 Unsafe 调用getInt方法时，因为一个int型变量占 4 个字节，会一次性读取 4 个字节，组成一个int的值，对应的十进制结果为 16843009。
     */
    @Test
    public void memoryTest(){
        Unsafe unsafe = reflectGetUnsafe();
        int size = 4;
        assert unsafe != null;
        //addr 是所在内存空间的地址
        long addr = unsafe.allocateMemory(size);
        long addr3 = unsafe.reallocateMemory(addr, size * 2);
        System.out.println("addr: "+addr);
        System.out.println("addr3: "+addr3);
        try {
            //给addr的内存赋值 一个字节 八个比特 也就是 addr的内存是  0000001 00000001 00000001 00000001  四个字节
            unsafe.setMemory(null,addr ,size,(byte)1);
            //把addr的内容复制到addr3 先复制四个字节  0000001 00000001 00000001 00000001 又复制四个字节0000001 00000001 00000001 00000001 拼到一起
            for (int i = 0; i < 2; i++) {
                unsafe.copyMemory(null,addr,null,addr3+size*i,4);
            }
            System.out.println(unsafe.getInt(addr));//十进制 16843009   二进制0000001 00000001 00000001 00000001
            System.out.println(unsafe.getLong(addr3));//十进制 72340172838076673 二进制 0000001 00000001 00000001 00000001 0000001 00000001 00000001 00000001
        }finally {
            //通过这种方式分配的内存属于 堆外内存 ，是无法进行垃圾回收的 得手动回收 不然会内存泄漏的
            unsafe.freeMemory(addr);
            unsafe.freeMemory(addr3);
        }
    }

    /**
     * 使用 Unsafe 的 allocateInstance 方法，允许我们使用非常规的方式进行对象的实例化
     * 分别基于构造函数、反射以及 Unsafe 方法的不同方式创建对象进行比较
     *
     * 常规对象实例化方式：我们通常所用到的创建对象的方式，从本质上来讲，都是通过 new 机制来实现对象的创建。
     * 但是，new 机制有个特点就是当类只提供有参的构造函数且无显示声明无参构造函数时，则必须使用有参构造函数进行对象构造，
     * 而使用有参构造函数时，必须传递相应个数的参数才能完成对象实例化。
     * 非常规的实例化方式：而 Unsafe 中提供 allocateInstance 方法，仅通过 Class 对象就可以创建此类的实例对象，
     * 而且不需要调用其构造函数、初始化代码、JVM 安全检查等。
     * 它抑制修饰符检测，也就是即使构造器是 private 修饰的也能通过此方法实例化，只需提类对象即可创建相应的对象。
     * 由于这种特性，allocateInstance 在 java.lang.invoke、Objenesis（提供绕过类构造器的对象生成方式）、Gson（反序列化时用到）中都有相应的应用
     */
    @Test
    public void test_allocateInstance() throws InstantiationException, IllegalAccessException {
        //不需要调用其构造函数、初始化代码、JVM 安全检查等。
        // 它抑制修饰符检测，也就是即使构造器是 private 修饰的也能通过此方法实例化，只需提类对象即可创建相应的对象
        Unsafe unsafe = reflectGetUnsafe();
        A a1 = new A();
        System.out.println(a1.getB());
        A a2 = A.class.newInstance();
        System.out.println(a2.getB());
        A a3 = (A)unsafe.allocateInstance(A.class);
        System.out.println(a3.getB());//打印结果分别为 1、1、0，说明通过allocateInstance方法创建对象过程中，不会调用类的构造方法
    }
    @Test
    public void test_clone(){
        System.out.println("试试clone push");
    }
}
