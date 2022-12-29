package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionDemo1 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, InvocationTargetException {
        Writer writer = new Writer();
        writer.setName("沉默王二");
        System.out.println(writer.getName());

        //Writer writer = new Writer(); 创建实例
        Class clazz = Class.forName("org.example.Writer");
        Constructor constructor = clazz.getConstructor();
        Object object = constructor.newInstance();

        //writer.setName("沉默王二"); 调用setName方法
        Method setNameMethod = clazz.getMethod("setName", String.class);
        setNameMethod.invoke(object, "沉默王二");

        //writer.getName() 调用getName()方法
        Method getNameMethod = clazz.getMethod("getName");
        System.out.println(getNameMethod.invoke(object));
    }
}
