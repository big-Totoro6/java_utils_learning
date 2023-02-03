package org.example;

import static org.junit.Assert.assertTrue;

import com.google.common.collect.Lists;
import org.about.annotation.Path;
import org.aboutstatic.Student;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.inner.*;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import org.three.character.oriented.BorderCollie;
import org.three.character.oriented.Dog;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.text.NumberFormat;
import java.time.LocalDate;
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

    @Test
    public void test_ReflectField() throws NoSuchFieldException {
        BorderCollie dog = new BorderCollie("边牧", 11, 1, 1);
        Class<? extends BorderCollie> dogClass = dog.getClass();
        Field[] fields = dogClass.getFields();//获取类的公共成员变量
        Field[] declaredFields = dogClass.getDeclaredFields();
        //基本的 getFields 是获取子类与父类的 公共成员变量
        //getDeclaredFields 是获取子类全部的成员变量
        //如果你要获取 子类与父类全部的成员变量呢 不论私有公有
        List<Field> allFields = Arrays.asList(FieldUtils.getAllFields(dogClass));
        allFields.forEach(System.out::println);
        /**  allFields.forEach(System.out::println);
         * private java.lang.String org.three.character.oriented.BorderCollie.specialBorder
         * private java.lang.String org.three.character.oriented.Dog.name
         * private java.lang.Integer org.three.character.oriented.Dog.age
         * private java.lang.Integer org.three.character.oriented.Dog.smartLevel
         * private java.lang.Integer org.three.character.oriented.Dog.sex
         * static java.lang.String org.three.character.oriented.Dog.iam
         */
    }

    /**
     * 越过泛型检查
     * 我有一个类ArrayList<Integer>集合，现在我想在这个集合中添加一个字符串数据，如何实现
     */
    @Test
    public void test_override_generic_check() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<Integer> array=new ArrayList<Integer>();
        array.add(10);
        array.add(20);
//        array.add("hello");
        Class<? extends ArrayList> c=array.getClass();
        Method m = c.getMethod("add", Object.class);
        m.invoke(array,"hello");
        m.invoke(array,"wrold");
        m.invoke(array,"java");

        System.out.println(array);
    }

    /**
     * 简单使用 reflections 框架
     */
    @Test
    public void test_simple_reflections(){
        //org.about.annotation为要扫描的包
        Reflections reflections = new Reflections("org.about.annotation");
        System.out.println(reflections);
        //获取type下的子类或接口的实现类
        Set<Class<? extends Serializable>> subTypesOf = reflections.getSubTypesOf(Serializable.class);
        //获取含有annotation注解的类或接口
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(Path.class);
        //获取含有annotation注解的方法
        Set<Method> methodsAnnotatedWith = reflections.getMethodsAnnotatedWith(Path.class);
        //获取含有annotation注解的构造器
        Set<Constructor> constructorsAnnotatedWith = reflections.getConstructorsAnnotatedWith(Path.class);
        //获取含有annotation注解的属性
        Set<Field> fieldsAnnotatedWith = reflections.getFieldsAnnotatedWith(Path.class);
    }

    /**
     * 复杂使用 reflections 框架
     */
    @Test
    public void test_hard_reflections(){
        //org.about.annotation为要扫描的包
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        //设置扫描的包
                        .forPackages("org.about")
                        //设置扫描器
                        // scanners下有扫描器
                        .addScanners(
                                //类和接口的扫描器
                                new SubTypesScanner(),
                                //用于扫描方法上注解扫描器
                                new MethodAnnotationsScanner(),
                                //用于扫描属性上注解扫描器
                                new FieldAnnotationsScanner()
                        )
                        //设置过滤器
                        .filterInputsBy(
                                new FilterBuilder()
                                        //需要要扫描的包
                                        .includePackage("org.about.annotation")
                                        //不需要要扫描的包
                                        .excludePackage("org.about.ser")
                        )
        );
    }

    /**
     * org.apache.commons.lang3 包提供的StringUtils工具类
     * 获取 open 与close 之间的字符串 如有多个匹配的 则获取多个
     *
     * 实现思路：
     * 按照open 与close 分别匹配字符所在的位置
     * 然后截取中间的字符串 加入List中 然后把pos 位置移到close匹配的索引那 继续匹配下面的
     * 只有当open和close都匹配上 才算一个合法的字符 否则就break
     */
    @Test
    public void test_substringsBetween(){
        String str="[1,2,3],[4,5],[6]";
        String[] strings = StringUtils.substringsBetween(str, "[", "]");
        Arrays.stream(strings).forEach(System.out::println);
        /**
         * 1,2,3
         * 4,5
         * 6
         */
    }

    /**
     * 为什么Java只有值传递
     */
    @Test
    public void test_valueTransferInJava(){
        /**值传递 ：方法接收的是实参值的拷贝，会创建副本。
         * 因为 方法中传递的是形参 在方法里面改变的都是形参的值，和主线程里的 num1 num2 无关
         */
        System.out.println("-----------------基础类型--------------------");
        int num1=1;
        int num2=2;
        System.out.println("before swap num1: "+ num1);
        System.out.println("before swap num2: "+ num2);
        swapBaseType(num1,num2);
        System.out.println("after swap num1: "+ num1);
        System.out.println("after swap num2: "+ num2);
        System.out.println("------------------引用类型-------------------");
        /**
         * 引用传递 ：方法接收的直接是实参所引用的对象在堆中的地址，不会创建副本，对形参的修改将影响到实参。
         * 也就是说 主线程里面 jason bro 是两个栈内的引用，是一个地址，指向堆内的对象， 方法传递就是把引用复制一份副本，
         * 你在里面交换副本的值，和外界的值又没关系，这个和上面的基础类型的值传递是一样的，只有你在方法里面改变了堆内对象的属性，
         * 你外界指向堆内的对象展现出来 自然也就不一样了
         * 所以 java是值传递
         */
        Student jason = new Student("Jason");
        Student bro=new Student("大妖怪");
        System.out.println("before swap Student1: "+ jason.getName());
        System.out.println("before swap Student2: "+ bro.getName());
        swapReferenceType(jason,bro);
        System.out.println("after swap Student1: "+ jason.getName());
        System.out.println("after swap Student2: "+ bro.getName());
    }

    /**
     * 交换两个基础变量
     * @param num1
     * @param num2
     */
    private void swapBaseType(int num1, int num2) {
        int temp=num1;
        num1=num2;
        num2=temp;
    }

    /**
     * 交换两个
     * @param student1
     * @param student2
     */
    private void swapReferenceType(Student student1, Student student2) {
        Student temp=student1;
        student1=student2;
        student2=temp;
    }
    @Test
    public void test_string_replaceAll(){
        String data="{{abc}}\"";
        System.out.println(data);
        String[] split = data.replace("{", "").replace("}", "").replace("\"", "").split(",");
        Arrays.stream(split).forEach(System.out::print);
    }

    /**
     * 转化成BigDecimal 需要用 String  字符串传参.0.0.0.0.0.0.0.0.0.0.0.
     */
    @Test
    public void test_BigDecimal(){
        /**
         * 因为BigDecimal(double) 这个构造器具有不可知性 因为0.1不能准确的表示为double 或者任何有限长度的二进制小数
         * 因为十进制小数到二进制小数 整数部分除 2 取余，逆序排列，小数部分使用乘 2 取整数位，顺序排列
         */
        BigDecimal a =new BigDecimal(0.1);
        System.out.println("a values is:"+a);//a values is:0.1000000000000000055511151231257827021181583404541015625
        System.out.println("=====================");
        BigDecimal b =new BigDecimal("0.676");
        System.out.println("b values is:"+b);//b values is:0.1
    }

    @Test
    public void test_double_lost_scale(){
        System.out.println(0.1 + 0.2);
        System.out.println(0.2 - 0.1);
        System.out.println(0.1 * 0.2);
        System.out.println(0.2 / 0.1);
        System.out.println(0.3 - 0.1);
        System.out.println(0.3 / 0.1);
        /**输出
         * 0.30000000000000004
         * 0.1
         * 0.020000000000000004
         * 2.0
         * 0.19999999999999998
         * 2.9999999999999996
         * 计算机是二进制的。浮点数没有办法是用二进制进行精确表示。
         * 我们的CPU表示浮点数由两个部分组成：指数和尾数，这样的表示方法一般都会失去一定的精确度，有些浮点数运算也会产生一定的误差。
         */
    }

    @Test
    public void test_BigDecimal_divide(){
        BigDecimal a = new BigDecimal("4.5");
        BigDecimal b = new BigDecimal("1.3");
//        System.out.println(a.divide(b));//java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result. 会抛异常
        //scale 表示保留几位小数   后面是四舍五入的取整RoundingMode.UP 直接向上取整    HALF_UP 四舍五入
        BigDecimal divide = a.divide(b, 5, RoundingMode.UP);
        System.out.println("a.divide(b, 2, RoundingMode.UP) = "+divide);
        //转百分数
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(2);
        System.out.println("BigDecimal转百分数:  "+ percent.format(divide.doubleValue()));
    }

    @Test
    public void test_LocalDate_CompareTo(){
        LocalDate a = LocalDate.of(2023, 3, 11);
        LocalDate b = LocalDate.of(2023, 3, 11);
        LocalDate c = LocalDate.of(2023, 3, 15);
        LocalDate d = LocalDate.of(2023, 3, 1);
        System.out.println(a.compareTo(b));
        System.out.println(a.compareTo(c));
        System.out.println(a.compareTo(d));

    }

}

