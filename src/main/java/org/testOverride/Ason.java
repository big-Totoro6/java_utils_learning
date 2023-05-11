package org.testOverride;

public class Ason<E> extends Afather<E> implements List<E>{

    @Override
    void get() {

    }

    public static void main(String[] args) {
        Ason<String> stringAson = new Ason<>();
        stringAson.add("1");
    }

    public void add(E e){
        System.out.println("its son");
    }

    @Override
    public void hhhh() {

    }
}
