package org.abstractAndAbstractClass;

public interface Runnable extends GoAble,Puttable{
    //接口是可以多继承的 其实接口的多继承就和 类的多实现类似
    Integer run = null;
    void go();
    static void walk(){
        System.out.println("爷在走");
    }
}
