package org.three.character.oriented;

import lombok.Data;

/**
 * 边牧
 */
@Data
public class BorderCollie extends Dog{
    private String specialBorder;
    /**
     * 当类中没有显式定义任何构造器时，由Java编译器默认提供的无参构造器；
     * 除了基类 Object 外，其他类内部的默认构造器都只有直接调用父类的空参构造器，即函数体是 super ()
     *
     * @param name
     * @param age
     * @param smartLevel
     * @param sex
     */
    public BorderCollie(String name, Integer age, Integer smartLevel, Integer sex) {
        super(name, age, smartLevel, sex);
    }

    /**
     * 当显示声明了有参构造器之后 就
     * 重载 方法名一样 方法签名不一样 构造器重载 最典型的
     */
    public BorderCollie() {

    }

    /**
     * 子类重写父类方法 重写就是 方法名 方法签名都一样 子类重写父类方法 这是最典型的override
     */
    @Override
    public String whoAmI() {
        return "i am BorderCollie";
    }
    public void itsBelongBorderCollie(){
        System.out.println("itsBelongBorderCollie");
    }

    public static void main(String[] args) {
        //Java 子类不会继承父类的构造方法 但
        BorderCollie borderCollie = new BorderCollie();
        borderCollie.setName("边牧");
        //子类可以获取父类的静态变量
        String iam = Dog.iam;
        //子类继承了父类 就拥有了父类的属性和方法
        borderCollie.whoAmI();
        //多态就是 子类可以用父类的类型 就边牧是边牧 但边牧也是狗 这就是向上转型 把子类的引用指向父类的对象
        Dog border = new BorderCollie();
        Dog chineseRuralDog= new ChineseRuralDog();
        //这样多态使用的father 只能调用父类的方法，如果子类重写了方法 就调用子类的，不能调用子类私有的
        System.out.println(border.whoAmI());
        System.out.println(chineseRuralDog.whoAmI());
        //多态的意义就是 同一种事物表现出多种形态 明明都是Dog这个对象 但是调用方法 却有不同的实现
        People people = new People();
        //我只要有一个方法 传父类的形参，不管我多了多少只狗，我只要继承父类 狗这个类 我就能溜它
        people.walkingDog(chineseRuralDog);
        people.walkingDog(borderCollie);
        people.walkingDog(chineseRuralDog);
        people.walkingDog(borderCollie);
    }
}
