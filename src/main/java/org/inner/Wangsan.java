package org.inner;

import lombok.Data;

@Data
public class Wangsan {
    private String name;
    public Wangsan print() {
        class Wangxiaosan extends Wangsan{
            private int age = 18;
        }
        return new Wangxiaosan();
    }
}