package org.aboutstatic;

import lombok.Data;

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
    static String school ="浙江工商大学";

    public Student() {
    }
    public Student(String name, Integer age, LocalDate now) {
        this.name = name;
        this.age = age;
        this.date=now;
    }


    public static void main(String[] args) {
        /**
         * 两个学生
         * s1 s2 这两个学生对象的引用变量放在栈（stack）内 ，栈中存放着指向堆区的引用
         * s1 s2 这两个学生对象存放在堆（heap）内
         * school 这个static声明的静态变量存放在静态区
         */
        LocalDate d = LocalDate.parse("2022/2/21", DateTimeFormatter.ofPattern("yyyy/M/d"));

        Student s1 = new Student("Jack", 18,LocalDate.parse("2022/2/21", DateTimeFormatter.ofPattern("yyyy/M/d")));
        Student s2 = new Student("Jason", 22, LocalDate.parse("2021/2/21", DateTimeFormatter.ofPattern("yyyy/M/d")));
        Student s3 = new Student(null, 22, LocalDate.now());
        Student s4 = new Student("lili", 19, null);
        Student s5 = new Student("null", null, null);
        ArrayList<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
        //stream按字段分组
//        Map<Integer, List<Student>> collect = students.stream().collect(Collectors.groupingBy(Student::getAge));
        //stream匹配 直接返回
        boolean b = students.stream().noneMatch(t ->t.getAge()!=null && t.getAge().equals(33));
        System.out.println(b);
        //stream 按日期排序
        List<Student> student = students.stream().filter(t->t.getDate()!=null).sorted(Comparator.comparing(Student::getDate,Comparator.reverseOrder())).collect(Collectors.toList());
        //stream 按日期排序 假如空值
        List<Student> student1 = students.stream().sorted(Comparator.comparing(Student::getDate,Comparator.nullsLast(Comparator.reverseOrder()))).collect(Collectors.toList());

        System.out.println(student);
//        List<Student> studentStream = students.stream().filter(t -> t.getAge() != null && !t.getAge().equals(0)).collect(Collectors.toList());
//        System.out.println(student.getDate());
    }
}
