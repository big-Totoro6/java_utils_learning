package org.abstractAndAbstractClass;

/**
 * 接口主要用于对类的行为进行约束，你实现了某个接口就具有了对应的行为。
 * 抽象类主要用于代码复用，强调的是所属关系。
 */
public class TestAbstract {
    public static void main(String[] args) {
        //'runnable' is abstract; cannot be instantiated 接口无法实例化
//        runnable runnable = new runnable();
        //抽象类能实例化
        RunningMan runningMan = new RunningMan() {
            @Override
            void run() {

            }
        };//用匿名内部类去实例化对象 这个对象不是这个抽象类 而是继承这个抽象类的对象 RunningMan这只是一个向上转型 抽象类是不能实例化的
        RunningMan man = new Man();
        man.eat();
        runningMan.eat();
        Man man1 = new Man();
        Runnable.walk();
    }

}
