package org.example;

import lombok.Data;

@Data
public class User implements Runnable{
    private String name;
    private double weight;
    private int age;
    private long id;
    private String address;

    @Override
    public void run() {
        System.out.println("Running");
    }
}
