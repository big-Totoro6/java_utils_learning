package org.deep.copy;

import lombok.Data;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Data
public class Man implements Serializable {
    String name;
    Integer age;
    Son son;
    public Man(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Man() {
    }


    public static void main(String[] args) {
        Man jason = new Man("Jason", 18);
        jason.setSon(new Son("Jason's son"));
        //这是浅拷贝 不论你是新建对象 还是引用 最终你的引用是指向jason这个在堆内新建内存的对象
        Man jack = new Man();
        jack = jason;
        Man lili=jason;
        System.out.println("jason == lili: " +(jason == lili));//== 比较的是值 也就是引用 这样说明 这个引用是指向同一个堆内对象
        System.out.println("jason == jack: " +(jason == jack));
        //深拷贝 就是新建一个堆内内存，完全复制那个堆内对象，当你对新的对象改变属性时，都与原对象无关了
        Man deepJason = new Man();
        BeanUtils.copyProperties(jason,deepJason);
        System.out.println("jason == deepJason: "+(jason == deepJason));
        deepJason.setName("deepJason");
        //当你里面有对象时 这样就不行了 就是你的对象里面的属性里 有别的对象 那你拷贝时 虽然新建了一个新的堆内对象，但你里面那个对象属性还是指向jason的son对象
        System.out.println("deepJason.son == jason.son: " + (deepJason.son == jason.son));
        //如果在拷贝时，把对方的对象属性也复制过来 可以用Apache Commons Lang序列化工具 但是这些类得实现支持序列化的接口，java是多实现的，所以方便
        Man cloneMan = SerializationUtils.clone(jason);
        System.out.println("cloneMan.son==jason.son : "+(cloneMan.son==jason.son));
    }
}
