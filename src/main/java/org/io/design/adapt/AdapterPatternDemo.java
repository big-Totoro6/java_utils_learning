package org.io.design.adapt;

/**
 * 意图：将一个类的接口转换成客户希望的另外一个接口。适配器模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。
 * 本来AudioPlayer 只有放mp3的能力 但是通过适配器去实现了本来这个类实现不了的实现 比如放mp4 vlc
 * 主要解决：主要解决在软件系统中，常常要将一些"现存的对象"放到新的环境中，而新环境要求的接口是现对象不能满足的。
 * 对外暴露的时候 其实就是调用AudioPlayer 不过通过适配器模式 给AudioPlayer装了一个适配器 通过适配器 他有了一些原本没有的能力
 * 举个例子：
 * 你像香港的switch充电器 的充电头是不能直接插到大陆的插座的，得有个适配器，你得先把充电器查到适配器上，再把适配器插到插座，这样就实现了充电
 *
 * 使用场景：有动机地修改一个正常运行的系统的接口，这时应该考虑使用适配器模式。
 *
 * 注意事项：适配器不是在详细设计时添加的，而是解决正在服役的项目的问题。
 */
public class AdapterPatternDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");
    }
}
