package org.three.character.oriented;

import lombok.Data;

/**
 * 狗这个类，基本的特征
 */
@Data
public class Dog {
    private String name;
    private Integer age;
    private Integer smartLevel;
    private Integer sex;
    static String iam="这是dog的标志";
    /**
     * 当类中没有显式定义任何构造器时，由Java编译器默认提供的无参构造器；
     * 除了基类 Object 外，其他类内部的默认构造器都只有直接调用父类的空参构造器，即函数体是 super ()
     * @param name
     * @param age
     * @param smartLevel
     * @param sex
     */
    public Dog(String name, Integer age, Integer smartLevel, Integer sex) {
        this.name = name;
        this.age = age;
        this.smartLevel = smartLevel;
        this.sex = sex;
    }

    public Dog() {
    }
    public String whoAmI(){
        return  "i am dog";
    }
    public void itsDog(){
        System.out.println("its Dog");
    }
}
