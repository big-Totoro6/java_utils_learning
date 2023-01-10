package org.reflect;

import org.three.character.oriented.Dog;
import org.three.character.oriented.People;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionDemo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //使用类的class属性类获取该类对应的Class对象
        Class<People> peopleClass = People.class;
        System.out.println(peopleClass);//class org.three.character.oriented.People
        System.out.println(peopleClass.getName());//org.three.character.oriented.People
        System.out.println(peopleClass.getConstructor());
        System.out.println(peopleClass.getClassLoader());
        System.out.println(Arrays.toString(peopleClass.getAnnotations()));
        System.out.println(Arrays.toString(peopleClass.getInterfaces()));
        Method walkingDog = peopleClass.getMethod("walkingDog", Dog.class);//第一个参数是方法名 第二个参数是方法形参的类型
        System.out.println(walkingDog);//public void org.three.character.oriented.People.walkingDog(org.three.character.oriented.Dog)
        walkingDog.invoke(new People(),new Dog());//第一个参数是 这个方法所在的类对象，你调用方法 是要有对象的， 后面是对象的传参

        //使用类的class属性类获取该类对应的Class对象
        People people = new People();
        Class<? extends People> aClass = people.getClass();
        //其实操作是没差的
    }
}
