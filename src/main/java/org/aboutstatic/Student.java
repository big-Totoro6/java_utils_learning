package org.aboutstatic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 如果浙商大录取了一万名学生 10万个学生对象，每个学生对象都有一个school字段 如果直接设置为私有字段，相当于占用一万个内存，如果将
 * static String school = "浙江工商大学" 那就只占用一块内存
 */
@Data
public class Student {
    private String name;
    private Integer age;
    private LocalDate date;
    private Integer sex;
    private Integer dept;
    private Integer canView;
    static String school ="浙江工商大学";

    public Student() {
    }
    public Student(String name) {
        this.name = name;
    }
    public Student(String name, Integer age, LocalDate now) {
        this.name = name;
        this.age = age;
        this.date=now;
    }

    public Student(String name, Integer age, LocalDate date, Integer sex) {
        this.name = name;
        this.age = age;
        this.date = date;
        this.sex = sex;
    }

    public Student(String name, Integer age, LocalDate date, Integer sex, Integer dept, Integer canView) {
        this.name = name;
        this.age = age;
        this.date = date;
        this.sex = sex;
        this.dept = dept;
        this.canView = canView;
    }

    public static void main(String[] args) {
        /**
         * 两个学生
         * s1 s2 这两个学生对象的引用变量放在栈（stack）内 ，栈中存放着指向堆区的引用
         * s1 s2 这两个学生对象存放在堆（heap）内
         * school 这个static声明的静态变量存放在静态区
         */
        Student s1 = new Student("Jack", 18,LocalDate.parse("2022/2/21", DateTimeFormatter.ofPattern("yyyy/M/d")));
        Student s2 = new Student("Jason", 22, LocalDate.parse("2021/2/21", DateTimeFormatter.ofPattern("yyyy/M/d")));
        ArrayList<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        System.out.println(s1.date);
        System.out.println(Student.school);
    }
}
