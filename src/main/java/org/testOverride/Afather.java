package org.testOverride;

public abstract class Afather<E> {
    public void add(E e){
        System.out.println("its father");
    }
    abstract void get();
}
