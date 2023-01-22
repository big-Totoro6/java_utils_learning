package org.three.character.face;

public class Driver{
    public void drive(Ride ride){
        ride.drive();
    }

    public static void main(String[] args) {
        //开车的人
        Driver driver = new Driver();
        B b = new B();
        Bwm bwm = new Bwm();
        Donkey donkey = new Donkey();
        driver.drive(b);
        driver.drive(bwm);
        driver.drive(donkey);
    }
}
