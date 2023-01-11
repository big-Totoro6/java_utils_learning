package org.io.design.factory;

/**
 * 意图：定义一个创建对象的接口，让其子类自己决定实例化哪一个工厂类，工厂模式使其创建过程延迟到子类进行。
 * 主要解决：主要解决接口选择的问题。
 */
public class FactoryPattern {
    public static void main(String[] args) {
        //通过创建工厂类 我只要知道类名 就可以返回我需要的对象，当我需要新的椭圆形这个类的话，我只需要改变ShapeFactory，增加椭圆的建造过程就好了
        //你只要知道，去问工厂提车，你只要shapeFactory.getShape("CIRCLE") 知道他的名字，就可以提车，不用管车怎么建造的啥的
        ShapeFactory shapeFactory = new ShapeFactory();
        //获取 Circle 的对象，并调用它的 draw 方法
        Shape shape1 = shapeFactory.getShape("CIRCLE");
        //调用 Circle 的 draw 方法
        shape1.draw();

        //获取 Rectangle 的对象，并调用它的 draw 方法
        Shape shape2 = shapeFactory.getShape("RECTANGLE");
        //调用 Rectangle 的 draw 方法
        shape2.draw();

        //获取 Square 的对象，并调用它的 draw 方法
        Shape shape3 = shapeFactory.getShape("SQUARE");
        //调用 Square 的 draw 方法
        shape3.draw();

    }
}
