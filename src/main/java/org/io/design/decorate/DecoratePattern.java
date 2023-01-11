package org.io.design.decorate;


import lombok.NoArgsConstructor;

/**
 * 意图：装饰器模式（Decorator Pattern）允许向一个现有的对象添加新的功能，同时又不改变其结构。
 * 主要解决：一般的，我们为了扩展一个类经常使用继承方式实现，由于继承为类引入静态特征，并且随着扩展功能的增多，子类会很膨胀。
 * 装饰器模式 更侧重于动态地增强原始类的功能，装饰器类需要跟原始类继承相同的抽象类或者实现相同的接口。并且，装饰器模式支持对原始类嵌套使用多个装饰器。
 */
public class DecoratePattern {
    public static void main(String[] args) {
        Server server = new Server();
        server.receive();
        System.out.println("-------decorate-----------");
        Decorate proxy = new Decorate(new Server());
        proxy.receive();
    }
}
class Server{
    public void receive(){
        System.out.println("处理请求");
    }
}
@NoArgsConstructor
class Decorate extends Server{
    private Server server;

    public Decorate(Server server) {
        this.server = server;
    }

    @Override
    public void receive(){
        server.receive();
        System.out.println("流量控制");
    }
}
