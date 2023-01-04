package org.whyStringNotBeChanged;

/**
 * 我们知道被 final 关键字修饰的类不能被继承，修饰的方法不能被重写，修饰的变量是基本数据类型则值不能改变，修饰的变量是引用类型则不能再指向其他对象。
 * 因此，final 关键字修饰的数组保存字符串并不是 String 不可变的根本原因，因为这个数组保存的字符串是可变的（final 修饰引用类型变量的情况）
 * String真正不可变的原因是：
 * 保存字符串的数组被 final 修饰且为私有的，并且String 类没有提供/暴露修改这个字符串的方法。  private final char value[];
 * String 类被 final 修饰导致其不能被继承，进而避免了子类破坏 String 不可变。
 *
 * Java 9 将 String 的底层实现由 char[] 改成了 byte[]
 * 新版的 String 其实支持两个编码方案： Latin-1 和 UTF-16。如果字符串中包含的汉字没有超过 Latin-1 可表示范围内的字符，那就会使用 Latin-1 作为编码方案。
 * char在jvm里面占用两个字节utf-8 0-65535 许多一个字节就能表示的 也得占用两个字节
 * 针对 JDK 9 的 String 源码里，为了区别编码方式，追加了一个 coder 字段来区分。
 * Java 会根据字符串的内容自动设置为相应的编码，要么 Latin-1 要么 UTF16。
 * 也就是说，从 char[] 到 byte[]，中文是两个字节，纯英文是一个字节，在此之前呢，中文是两个字节，英文也是两个字节 。
 *
 * 为什么用UTF-16而不用UTF-8呢？
 * 0xxxxxxx：一个字节；
 * 110xxxxx 10xxxxxx：两个字节编码形式（开始两个 1）
 * 1110xxxx 10xxxxxx 10xxxxxx：三字节编码形式（开始三个 1）；
 * 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx：四字节编码形式（开始四个 1）。
 * 也就是说，UTF-8 是变长的，那对于 String 这种有随机访问方法的类来说，就很不方便。所谓的随机访问，就是charAt、subString这种方法，
 * 随便指定一个数字，String要能给出结果。如果字符串中的每个字符占用的内存是不定长的，那么进行随机访问的时候，就需要从头开始数每个字符的长度，
 * 才能找到你想要的字符。
 *
 * UTF-16也是变长的呢？一个字符还可能占用 4 个字节呢？
 *
 * 的确，UTF-16 使用 2 个或者 4 个字节来存储字符。
 *
 * 对于 Unicode 编号范围在 0 ~ FFFF 之间的字符，UTF-16 使用两个字节存储。
 * 对于 Unicode 编号范围在 10000 ~ 10FFFF 之间的字符，UTF-16 使用四个字节存储，具体来说就是：将字符编号的所有比特位分成两部分，
 * 较高的一些比特位用一个值介于 D800~DBFF 之间的双字节存储，较低的一些比特位（剩下的比特位）用一个值介于 DC00~DFFF 之间的双字节存储。
 * 但是在 Java 中，一个字符（char）就是 2 个字节，占 4 个字节的字符，在 Java 里也是用两个 char 来存储的，而String的各种操作，
 * 都是以Java的字符（char）为单位的，charAt是取得第几个char，subString取的也是第几个到第几个char组成的子串，甚至length返回的都是char的个数。
 *
 * 所以UTF-16在Java的世界里，就可以视为一个定长的编码。
 */
public class TestString {
    public static void main(String[] args) {
        String a="a";

    }
}
