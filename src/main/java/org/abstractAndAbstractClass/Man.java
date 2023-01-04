package org.abstractAndAbstractClass;

public class Man extends RunningMan implements runnable{
    @Override
    void run() {
        System.out.println("run");
    }

    @Override
    public void go() {

    }
}
