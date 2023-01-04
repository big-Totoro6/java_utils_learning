package org.three.character.oriented;

public class People {
    public void walkingDog(BorderCollie borderCollie){
        System.out.println("我在遛"+borderCollie.whoAmI());
    }
    public void walkingDog(ChineseRuralDog chineseRuralDog){
        System.out.println("我在遛"+chineseRuralDog.whoAmI());
    }
    //总不能我有几百只狗 我就写几百个方法去遛狗吧
    public void walkingDog(Dog dog){
        System.out.println("我在遛"+dog.whoAmI());
    }
}
