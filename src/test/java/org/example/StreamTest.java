package org.example;

import org.aboutstatic.Student;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamTest {
    private static List<Student> students=new ArrayList<>();
    static {
        Student s1 = new Student("Jack", 18, LocalDate.parse("2022/2/21", DateTimeFormatter.ofPattern("yyyy/M/d")),1);
        Student s2 = new Student("Jason", 22, LocalDate.parse("2021/2/21", DateTimeFormatter.ofPattern("yyyy/M/d")),1);
        Student s3 = new Student(null, 22, LocalDate.now(),2);
        Student s4 = new Student("lili", 19, null,2);
        Student s5 = new Student("null", null, null,2);
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
    }

    /**
     * stream 过滤
     */
    @Test
    public void testFilter(){
        //取出年龄不为null的学生
        List<Student> collect = students.stream().filter(t -> t.getAge() != null).collect(Collectors.toList());
        System.out.println(collect.size());
    }
    /**
     * stream 排序
     * 排序要注意null值判断
     */
    @Test
    public void testSort(){
        //按年龄排序 加入null值比较 正序
        List<Student> sortByAge = students.stream().sorted(Comparator.comparing(Student::getAge,Comparator.nullsLast(Comparator.naturalOrder()))).collect(Collectors.toList());
        //按日期排序 加入null值比较 正序  (倒序用这个 Comparator.reverseOrder()
        List<Student> sortByDate = students.stream().sorted(Comparator.comparing(Student::getDate, Comparator.nullsLast(Comparator.naturalOrder()))).collect(Collectors.toList());
        //多重排序 正序年龄 倒序日期
        List<Student> sortAgeAndDate = students.stream().sorted(Comparator.comparing(Student::getAge, Comparator.nullsLast(Comparator.nullsLast(Comparator.naturalOrder())))
                .thenComparing(Student::getDate, Comparator.nullsLast(Comparator.reverseOrder()))).collect(Collectors.toList());
        System.out.println(students);
    }
    /**
     * stream 重组
     */
    @Test
    public void testCollect(){
        //流转换成List
        List<Student> toList = students.stream().filter(t -> t.getAge() != null).collect(Collectors.toList());
        //按照字段分组 流转换成map
        Map<Integer, List<Student>> toGroupMap = students.stream().collect(Collectors.groupingBy(Student::getSex));
        System.out.println(toList);
    }


}
