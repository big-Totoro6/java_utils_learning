package org.example;

import lombok.Data;
import org.aboutstatic.Student;
import org.apache.commons.collections.MapUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class StreamTest {
    private static List<Student> students=new ArrayList<>();
    static {
        Student s1 = new Student("Jack", 18, LocalDate.parse("2022/2/21", DateTimeFormatter.ofPattern("yyyy/M/d")),1,1,1);
        Student s2 = new Student("Jason", 22, LocalDate.parse("2021/2/21", DateTimeFormatter.ofPattern("yyyy/M/d")),1,1,0);
        Student s3 = new Student("nono", 22, LocalDate.now(),2,2,1);
        Student s4 = new Student("lili", 19, null,2,2,33);
        Student s5 = new Student("null", 22, null,2,2,1);
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
    @Test
    public void testToString(){
        String s = students.stream().map(Student::getName).collect(Collectors.joining(","));
        System.out.println(s);
        //把long集合转为string 并且按照 , 分隔成字符串
        String collect = students.stream().map(Student::getAge ).map(String::valueOf).collect(Collectors.joining(","));
        System.out.println(collect);
    }

    public static <T> Map<String, Object> objectToMap(T t, String... ignoreFields) {
        Map<String, Object> map = new HashMap<>();
        Field[] declaredFields = t.getClass().getDeclaredFields();
        List<String> ignoreFieldList = Arrays.asList(ignoreFields);
        Arrays.stream(declaredFields).forEach(data -> {
            data.setAccessible(true);
            try {
                if (ignoreFieldList.isEmpty() || !ignoreFieldList.contains(data.getName())) {
                    map.put(data.getName(), data.get(t));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return map;
    }
    @Test
    public void test_object_to_map(){
        ArrayList<Map> list = new ArrayList<>();
        students.forEach(t->{
            list.add(objectToMap(t));
        });

        ArrayList<StudentDto> studentDtos = new ArrayList<>();
        Map<String, List<Map>> collect = list.stream().collect(Collectors.groupingBy(o -> o.get("dept") + "_" + o.get("sex")));
        collect.forEach((key,value)->{
            StudentDto studentDto = new StudentDto();
            String[] s = key.split("_");
            String dept=s[0];
            String sex=s[1];
            studentDto.setDept(Integer.valueOf(dept));
            studentDto.setSex(Integer.valueOf(sex));
            int canView = value.stream().mapToInt(o -> (Integer) (o.get("canView"))).sum();
            studentDto.setCanView(canView);
            studentDtos.add(studentDto);
        });

        System.out.println();
    }

    @Test
    public void test_String_format(){
        String msg="Hello {0} 是你{0} 我要骂人了{1}";
        String jason = String.format(msg, "Jason", 11);
        String jason1 = MessageFormat.format(msg, "Jason", 11);
        System.out.println(msg);
        System.out.println(jason);
        System.out.println(jason1);
    }


}
@Data
class StudentDto{
    private String name;
    private Integer age;
    private Integer sex;
    private Integer dept;
    private Integer canView;
}
