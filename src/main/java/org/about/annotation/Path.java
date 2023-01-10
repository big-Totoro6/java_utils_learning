package org.about.annotation;

import org.aboutstatic.Student;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 这叫元注解 @interface 本质就是接口
 * public @interface SaveParam   等同 public interface SaveParam extends java.lang.annotation.Annotation
 * @Retention 注解被保留的阶段
 * RetentionPolicy.SOURCE  //在源代码有效,到.class被抛弃
 * RetentionPolicy.CLASS   //在编译阶段有效
 * RetentionPolicy.RUNTIME  //运行阶段有效
 *
 * @Target 用来定义注解的使用范围
 * ElementType.TYPE   //作用在类上
 * ElementType.FIELD  //作用在属性上
 * ElementType.METHOD //作用在方法上
 * ElementType.CONSTRUCTOR //作用在构造方法上
 * ElementType.ANNOTATION_TYPE //作用在注解上
 *
 * @Documented 在使用javadoc时，类上是否生成注解文档
 * @Inherited 注解可继承
 * @Repeatable 是否允许在同一位置重复定义注解
 */
@Retention(RetentionPolicy.RUNTIME)//
public @interface Path {

    /**
     *属性的返回值类型
     * 基本数据类型、String、枚举、注解、Class<? >、以上类型的数组
     */
    String name = "SaveParam";

    //枚举类
    Size SIZE();

    //String类
    String path();

    //基本数据类型 如果定义属性时使用default关键字给属性默认初始值，则使用注解时，可以不赋值
    int ag() default 1;

    //数组 数组属性赋值时，值使用{}包裹，如果数组中只有一个值，则{}省略
    String[] arra();

    //class
    Class c();

    //不可以 Invalid type 'Student' for annotation member
//    Student s();
}
enum Size {
    X,
    XL,
    XXL,
    XXXL
}
