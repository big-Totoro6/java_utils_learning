package org.about.annotation;

import java.io.Serializable;
import java.util.Arrays;

@Path(SIZE = Size.XXL, path = "/asd", arra = {"Jason"}, c = Integer.class)
public class Demo implements Serializable {
    public static void main(String[] args) {
        //Demo 这个类上 是否有 Path类型注解
        boolean annotationPresent = Demo.class.isAnnotationPresent(Path.class);
        System.out.println("Demo.class.isAnnotationPresent(Path.class) :"+ annotationPresent);
        System.out.print("Demo.class.getAnnotations(): ");
        Arrays.asList(Demo.class.getAnnotations()).forEach(System.out::println);
    }
}
