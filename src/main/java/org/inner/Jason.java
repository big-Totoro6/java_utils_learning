package org.inner;

import lombok.Data;

@Data
public class Jason {
    int age = 18;
    private String name = "Jaosn";
    static double money = 1;

    public Jason(){
        new Jack().print();
    }
    class Jack {
        int age = 81;

        public void print() {
            System.out.println(name);
            System.out.println(money);
            System.out.println("Jack:"+age);
            System.out.println("Jason:"+Jason.this.age);
        }
    }
}
