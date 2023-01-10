package org.example;

import static org.junit.Assert.assertTrue;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.inner.*;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import org.springframework.stereotype.Component;
import org.three.character.oriented.Dog;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.*;


/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void test_collectionUtils_isEmpty() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.add(3);

        if (CollectionUtils.isEmpty(list)) {
            System.out.println("集合为空");
        }
        if (CollectionUtils.isNotEmpty(list)) {
            System.out.println("集合不为空");
        }
    }

    @Test
    public void test_collectionUtils_union() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.add(3);

        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(4);

        //获取并集
        Collection<Integer> unionList = CollectionUtils.union(list, list2);
        System.out.println(unionList);

        //获取交集
        Collection<Integer> intersectionList = CollectionUtils.intersection(list, list2);
        System.out.println(intersectionList);

        //获取交集的补集
        Collection<Integer> disjunctionList = CollectionUtils.disjunction(list, list2);
        System.out.println(disjunctionList);

        //获取差集
        Collection<Integer> subtractList = CollectionUtils.subtract(list, list2);
        System.out.println(subtractList);
    }

    @Test
    public void test_stringutils_judge_null() {
        String str1 = null;
        String str2 = "";
        String str3 = " ";
        String str4 = "abc";
        //StringUtils.isEmpty() 判断null 和空串
        System.out.println(StringUtils.isEmpty(str1));
        System.out.println(StringUtils.isEmpty(str2));
        System.out.println(StringUtils.isEmpty(str3));
        System.out.println(StringUtils.isEmpty(str4));
        System.out.println("=====");
        System.out.println(StringUtils.isNotEmpty(str1));
        System.out.println(StringUtils.isNotEmpty(str2));
        System.out.println(StringUtils.isNotEmpty(str3));
        System.out.println(StringUtils.isNotEmpty(str4));
        System.out.println("=====");
        //StringUtils.isBlank 判断null 空串 还有空格
        System.out.println(StringUtils.isBlank(str1));
        System.out.println(StringUtils.isBlank(str2));
        System.out.println(StringUtils.isBlank(str3));
        System.out.println(StringUtils.isBlank(str4));
        System.out.println("=====");
        System.out.println(StringUtils.isNotBlank(str1));
        System.out.println(StringUtils.isNotBlank(str2));
        System.out.println(StringUtils.isNotBlank(str3));
        System.out.println(StringUtils.isNotBlank(str4));
    }

    @Test
    public void test_stringutils_split() {
        String str1 = null;
        //可判断空
        System.out.println(StringUtils.split(str1, ","));
        System.out.println(str1.split(","));
    }

    @Test
    public void test_stringutils_isNumeric() {
        String str1 = "123";
        String str2 = "123q";
        String str3 = "0.33";
        System.out.println(StringUtils.isNumeric(str1));
        System.out.println(StringUtils.isNumeric(str2));
        System.out.println(StringUtils.isNumeric(str3));
    }

    @Test
    public void test_stringutils_join() {
        List<String> list = Lists.newArrayList("a", "b", "c");
        List<Integer> list2 = Lists.newArrayList(1, 2, 3);
        System.out.println(StringUtils.join(list, ","));
        System.out.println(StringUtils.join(list2, " "));
    }

    @Test
    public void test_classutils() {
        //获取某个对象所有接口
        Class<?>[] allInterfaces = ClassUtils.getAllInterfaces(new User());
        //获取某个类的包名
        String packageName = ClassUtils.getPackageName(User.class);
        System.out.println(packageName);
        //判断某个类是否内部类
        System.out.println(ClassUtils.isInnerClass(User.class));
        //判断对象是否代理对象
        System.out.println(ClassUtils.isCglibProxy(new User()));
    }

    @Test
    public void test_beanutils() {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("苏三说技术");
        user1.setAddress("成都");
        //拷贝对象的属性
        User user2 = new User();
        BeanUtils.copyProperties(user1, user2);
        System.out.println(user2);
        //实例化某个类
        User user = BeanUtils.instantiateClass(User.class);
        System.out.println(user);
        //获取某个类的指定方法
        Method declaredMethod = BeanUtils.findDeclaredMethod(User.class, "getId");
        System.out.println(declaredMethod.getName());
        //获取指定方法的参数
        Method declaredMethod1 = BeanUtils.findDeclaredMethod(User.class, "getId");
        PropertyDescriptor propertyForMethod = BeanUtils.findPropertyForMethod(declaredMethod1);
        System.out.println(propertyForMethod.getName());
    }

    @Test
    public void test_reflectionutils() {
        //获取方法
        Method method = ReflectionUtils.findMethod(User.class, "getId");
        //获取字段
        Field field = ReflectionUtils.findField(User.class, "id");
        //执行方法 通过反射调用某个方法
//        ReflectionUtils.invokeMethod(method, SpringContextUtils.getBean(beanName), param);


    }

    @Test
    public void test_inner_class01() {
        //内部类依附于外部类
        //内部类与外部类绑定的很死
//        Jason jason = new Jason();
//        System.out.println(jason.getName());

        Wangsan wangsan = new Wangsan();
        wangsan.setName("wangsan");
        Wangsan print = wangsan.print();
        System.out.printf(print.toString());
    }

    /**
     * 判断素数
     *
     * @param num
     * @return
     */
    public boolean is_primary(int num) {
        boolean flag = true;
        for (int i = 2; i < num / 2; i++) {
            if (num % i == 0) {//能被整除 就不是素数
                flag = false;
                break;
            }
        }
        if (flag) {
            return true;
        }
        return false;
    }

    /**
     * 判断回文数
     *
     * @param target
     * @return
     */
    public boolean is_palindrome(int target) {
        boolean flag = true;
        //把目标数变成string
        String s = String.valueOf(target);
        for (int i = 0; i < (s.length() - 1) / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i)) {
                flag = false;
                break;
            }
        }
        if (flag) {
            return true;
        }
        return false;
    }

    @Test
    public void test_zero() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int count = 0;
        //遍历从N-M的数
        for (int i = N; i <= M; i++) {
            //对这个数进行判断 是否是素数
            if (is_primary(i)) {
                //判断是否回文
                if (is_palindrome(i)) {
                    count++;
                }
            }
        }
    }

    @Test
    public void test_goods() {
        Goods p = new Goods();
        //实际操作就是p.cont();  p.dest("Beijing"); 通过外部类来调用 隐藏内部类 实现封装
        Contents c = p.cont();
        Destination d = p.dest("Beijing");
        //内部类的构造器跟随类的定义 类是private 默认 构造器就是private
//        'org.inner.Goods.Content' has private access in 'org.inner.Goods'
//        p.new Content
//        p.new GDestination
//        'org.inner.Goods.GDestination' has protected access in 'org.inner.Goods'
//        p.new GDestination("ss")
        System.out.println(d.readLabel());
    }

    /**
     * 测试nio 缓存区
     */
    @Test
    public void test_nio_buffer() {
        // 创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 看一下初始时4个核心变量的值
        System.out.println("初始时-->limit--->" + byteBuffer.limit());
        System.out.println("初始时-->position--->" + byteBuffer.position());
        System.out.println("初始时-->capacity--->" + byteBuffer.capacity());
        System.out.println("初始时-->mark--->" + byteBuffer.mark());

        System.out.println("--------------------------------------");

        // 添加一些数据到缓冲区中
        String s = "沉默王二";
        byteBuffer.put(s.getBytes());

        // 看一下初始时4个核心变量的值
        System.out.println("put完之后-->limit--->" + byteBuffer.limit());
        System.out.println("put完之后-->position--->" + byteBuffer.position());
        System.out.println("put完之后-->capacity--->" + byteBuffer.capacity());
        System.out.println("put完之后-->mark--->" + byteBuffer.mark());
    }

    @Test
    public void testStartMethod() {
        Thread t = new Thread(new java.lang.Runnable() {
            @Override
            public void run() {
                System.out.println("不教胡马度阴山");
            }
        });
        System.out.println("第一次调用t.start()");
        t.start();
        System.out.println("第二次调用t.start()");
        t.start();
        //IllegalThreadStateException 第一次调用会报不合法线程状态异常
    }

    @Test
    public void blockedTest() throws InterruptedException {
//        处于BLOCKED状态的线程是因为在等待锁的释放
//        假如这里有两个线程a和b，a线程提前获得了锁并且暂未释放锁，此时b就处于BLOCKED状态
        Thread a = new Thread(new java.lang.Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        }, "a");
        Thread b = new Thread(new java.lang.Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        }, "b");
//      第一种状况
//        a.start();
//        b.start();
//        System.out.println(a.getName() + ":" + a.getState()); // 输出？RUNNABLE  RUNNABLE
//        System.out.println(b.getName() + ":" + b.getState()); // 输出？BLOCKED   RUNNABLE
//        cpu效率高一点，线程启动，还没进行run方法时，主线程就打印这两个线程的状态，这时两个线程都还没开始争抢锁，就都是Runnable状态
//        效率第一点，，那还没运行到打印，a抢到了锁，b在等，这个时候就是 a:runnable b:blocked


//        第二种状况
//        a.start();
//        Thread.sleep(1000L); // 需要注意这里main线程休眠了1000毫秒，而testMethod()里休眠了2000毫秒
//        b.start();
//        System.out.println(a.getName() + ":" + a.getState()); // 输出？
//        System.out.println(b.getName() + ":" + b.getState()); // 输出？
//        a:TIMED_WAITING   a抢到锁 开始休眠
//        b:BLOCKED         b开始等锁
//
//        a:TIMED_WAITING   a抢到锁 开始休眠
//        b:RUNNABLE        b还没运行run方法 还没抢锁

//        第三种状况
//        a.start();
//        a.join();//join方法 在主线程中执行，main线程会等a执行完再往下走
//        b.start();
//        System.out.println(a.getName() + ":" + a.getState()); // 输出 TERMINATED
//        System.out.println(b.getName() + ":" + b.getState());// b:TIMED_WAITING

//        第四种情况
        a.start();
        a.join(1000L);
        b.start();
        System.out.println(a.getName() + ":" + a.getState()); // 输出 TIEMD_WAITING
        System.out.println(b.getName() + ":" + b.getState()); // 不固定（RUNNABLE或BLOCKED）
    }

    // 同步方法争夺锁
    private synchronized void testMethod() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_threadGroup() {
        Thread testThread = new Thread(() -> {
            System.out.println("testThread当前线程组名字：" +
                    Thread.currentThread().getThreadGroup().getName());
            System.out.println("testThread线程名字：" +
                    Thread.currentThread().getName());
        });

        testThread.start();
        System.out.println("执行main所在线程的线程组名字： " + Thread.currentThread().getThreadGroup().getName());
        System.out.println("执行main方法线程名字：" + Thread.currentThread().getName());
    }

    @Test
    public void test_thread_priority() {
        Thread a = new Thread();
        System.out.println("我是默认线程优先级：" + a.getPriority());
        Thread b = new Thread();
        b.setPriority(10);
        System.out.println("我是设置过的线程优先级：" + b.getPriority());
    }

    /**
     * optional判断一个对象不为空并且对象的某个字段不为空
     * User对象非空就返回User的大写,如果User对象为空就返回"invalid
     * 避免使用== null这种判断空的方法
     */
    @Test
    public void test_optional_map() {
        List<User> users = new ArrayList<User>();
        User user = new User();
        user.setId(22);
        user.setName("Jason");
        Optional<User> userOptional = Optional.ofNullable(user);
        //map就是映射 把它本身转换成一个属性
        String userName = userOptional.map(t -> t.getName()).orElse("invalid");

        System.out.println(userName);
    }

    @Test
    public void test_process() {
        boolean flag = false;
        for (int i = 0; i <= 3; i++) {
            if (i == 0) {
                System.out.println("0");
            } else if (i == 1) {
                System.out.println("1");
                continue;
            } else if (i == 2) {
                System.out.println("2");
                flag = true;
            } else if (i == 3) {
                System.out.println("3");
                break;
            } else if (i == 4) {
                System.out.println("4");
            }
            System.out.println("xixi");
        }
        if (flag) {
            System.out.println("haha");
            return;
        }
        System.out.println("heihei");
    }

    @Test
    public void test_equal() {
        String a = new String("ab"); // a 为一个引用
        String b = new String("ab"); // b为另一个引用,对象的内容一样
        String aa = "ab"; // 放在常量池中
        String bb = "ab"; // 从常量池中查找
        Integer a1 = 42;
        double a2 = 42.0;
        System.out.println(aa == bb);// true
        System.out.println(a == b);// false
        System.out.println(a.equals(b));// true
        System.out.println(422 == 422.000);// true 基本类型的== 就是值比较
        System.out.println(a1 == a2);// true
    }

    @Test
    public void test_split(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("112,113");
        strings.add("112");
        strings.forEach(t->{
            List split = Arrays.asList(t.split(","));
            System.out.println(split.get(0));
        });
    }
    @Test
    public void test_reflect() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //实操 通过反射实现
        Dog dog = new Dog("Dog", 11, 2, 1);
        System.out.println(dog.getName());

        //通过反射实现

        //获取class对象
        Class<Dog> dogClass = Dog.class;
        //获取构造器
        Constructor<Dog> constructor = dogClass.getConstructor(String.class, Integer.class, Integer.class, Integer.class);
        constructor.setAccessible(true);
        Dog dog1 = constructor.newInstance("Dog", 11, 2, 1);
        System.out.println(dog1.getName());
    }
}

