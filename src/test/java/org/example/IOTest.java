package org.example;

import org.junit.Test;

import java.io.*;

public class IOTest {
    /**字节流
     * InputStream用于从源头（通常是文件）读取数据（字节信息）到内存中，
     * java.io.InputStream抽象类是所有字节输入流的父类。
     *
     * InputStream 常用方法 ：read() ：返回输入流中下一个字节的数据。返回的值介于 0 到 255 之间。如果未读取任何字节，则代码返回 -1 ，表示文件结束。
     * read(byte b[ ]) : 从输入流中读取一些字节存储到数组 b 中。如果数组 b 的长度为零，则不读取。如果没有可用字节读取，返回 -1。如果有可用字节读取，则最多读取的字节数最多等于 b.length ， 返回读取的字节数。这个方法等价于 read(b, 0, b.length)。
     * read(byte b[], int off, int len) ：在read(byte b[ ]) 方法的基础上增加了 off 参数（偏移量）和 len 参数（要读取的最大字节数）。
     * skip(long n) ：忽略输入流中的 n 个字节 ,返回实际忽略的字节数。
     * available() ：返回输入流中可以读取的字节数。
     * close() ：关闭输入流释放相关的系统资源。
     * 从 Java 9 开始，InputStream 新增加了多个实用的方法：
     * readAllBytes() ：读取输入流中的所有字节，返回字节数组。
     * readNBytes(byte[] b, int off, int len) ：阻塞直到读取 len 个字节。
     * transferTo(OutputStream out) ： 将所有字节从一个输入流传递到一个输出流。

     */
    @Test
    public void test_inputStream(){
        //使用try with resource 方式
        //FileInputStream 是一个比较常用的字节输入流对象，可直接指定文件路径，可以直接读取单字节数据，也可以读取至字节数组中
        try (InputStream file = new FileInputStream("G:\\MyProjects\\Java\\java_utils_learning\\src\\main\\java\\org\\io\\nio\\source.txt")) {
            System.out.println("Number of remaining bytes:" + file.available());
            long skip = file.skip(2);
            int content;
            System.out.println("The actual number of bytes skipped:" + skip);
            System.out.print("The content read from file:");
            while ((content = file.read()) != -1) {
                System.out.print((char) content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * OutputStream（字节输出流）OutputStream用于将数据（字节信息）写入到目的地（通常是文件），java.io.OutputStream抽象类是所有字节输出流的父类。OutputStream
     * 常用方法 ：
     * write(int b) ：将特定字节写入输出流。
     * write(byte b[ ]) : 将数组b 写入到输出流，等价于 write(b, 0, b.length) 。
     * write(byte[] b, int off, int len) : 在write(byte b[ ]) 方法的基础上增加了 off 参数（偏移量）和 len 参数（要读取的最大字节数）。
     * flush() ：刷新此输出流并强制写出所有缓冲的输出字节。
     * close() ：关闭输出流释放相关的系统资源。
     */
    @Test
    public void test_outputStream(){
        //FileOutputStream 是最常用的字节输出流对象，可直接指定文件路径，可以直接输出单字节数据，也可以输出指定的字节数组。
        try (FileOutputStream output = new FileOutputStream("G:\\MyProjects\\Java\\java_utils_learning\\src\\main\\java\\org\\io\\nio\\target.txt")) {
            byte[] array = "Jason's home is not in langlang mountain".getBytes();
            output.write(array);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //不管是文件读写还是网络发送接收，信息的最小存储单元都是字节。 那为什么 I/O 流操作要分为字节流操作和字符流操作呢？？？

    /**两个原因
     * 字符流是由 Java 虚拟机将字节转换得到的，这个过程还算是比较耗时。
     * 如果我们不知道编码类型就很容易出现乱码问题。 把上面source.txt里面加几个中文字符 就会出现乱码
     *
     * 因此，I/O 流就干脆提供了一个直接操作字符的接口，方便我们平时对字符进行流操作。
     * 如果音频文件、图片等媒体文件用字节流比较好，
     * 如果涉及到字符的话使用字符流比较好
     *
     * tip:
     * 字符流默认采用的是 Unicode 编码，我们可以通过构造方法自定义编码。
     * 顺便分享一下之前遇到的笔试题：
     * 常用字符编码所占字节数？
     * utf8 :英文占 1 字节，中文占 3 字节，
     * unicode：任何字符都占 2 个字节，
     * gbk：英文占 1 字节，中文占 2 字节。
     */

    /**
     * Reader（字符输入流）Reader用于从源头（通常是文件）读取数据（字符信息）到内存中，java.io.Reader抽象类是所有字符输入流的父类。
     * Reader 用于读取文本， InputStream 用于读取原始字节。
     * Reader 常用方法 ：
     * read() : 从输入流读取一个字符。
     * read(char[] cbuf) : 从输入流中读取一些字符，并将它们存储到字符数组 cbuf中，等价于 read(cbuf, 0, cbuf.length) 。
     * read(char[] cbuf, int off, int len) ：在read(char[] cbuf) 方法的基础上增加了 off 参数（偏移量）和 len 参数（要读取的最大字符数）。
     * skip(long n) ：忽略输入流中的 n 个字符 ,返回实际忽略的字符数。
     * close() : 关闭输入流并释放相关的系统资源。
     * InputStreamReader 是字节流转换为字符流的桥梁，其子类 FileReader 是基于该基础上的封装，可以直接操作字符文件。
     */

    @Test
    public void test_reader(){
        try (FileReader fileReader = new FileReader("G:\\MyProjects\\Java\\java_utils_learning\\src\\main\\java\\org\\io\\nio\\source.txt");) {
            int content;
            long skip = fileReader.skip(3);
            System.out.println("The actual number of bytes skipped:" + skip);
            System.out.print("The content read from file:");
            while ((content = fileReader.read()) != -1) {
                System.out.print((char) content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writer（字符输出流）Writer用于将数据（字符信息）写入到目的地（通常是文件），java.io.Writer抽象类是所有字节输出流的父类。
     * Writer 常用方法 ：write(int c) : 写入单个字符。
     * write(char[] cbuf) ：写入字符数组 cbuf，等价于write(cbuf, 0, cbuf.length)。
     * write(char[] cbuf, int off, int len) ：在write(char[] cbuf) 方法的基础上增加了 off 参数（偏移量）和 len 参数（要读取的最大字符数）。write(String str) ：写入字符串，等价于 write(str, 0, str.length()) 。
     * write(String str, int off, int len) ：在write(String str) 方法的基础上增加了 off 参数（偏移量）和 len 参数（要读取的最大字符数）。
     * append(CharSequence csq) ：将指定的字符序列附加到指定的 Writer 对象并返回该 Writer 对象。
     * append(char c) ：将指定的字符附加到指定的 Writer 对象并返回该 Writer 对象。
     * flush() ：刷新此输出流并强制写出所有缓冲的输出字符。
     * close():关闭输出流释放相关的系统资源。
     * OutputStreamWriter 是字符流转换为字节流的桥梁，其子类 FileWriter 是基于该基础上的封装，可以直接将字符写入到文件。
     */
    @Test
    public void test_write(){
        //向上转型不能一下跨越一个父类 访问到爷爷
        try (OutputStreamWriter output = new FileWriter("G:\\MyProjects\\Java\\java_utils_learning\\src\\main\\java\\org\\io\\nio\\target.txt")) {
            output.write("我是浪浪山上的一直野猪精");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //实际上IO操作是非常耗性能的 不是直接拿着字符流 字节流 就直接输入输出 这样很消耗性能 普遍的是用缓冲流
    //缓冲流将数据加载至缓冲区，一次性读取/写入多个字节，从而避免频繁的 IO 操作，提高流的传输效率。
    //字节缓冲流这里采用了装饰器模式来增强 InputStream 和OutputStream子类对象的功能。
    @Test
    public void test_bufferInputStream(){
        //向上转型不能一下跨越一个父类 访问到爷爷
        try (OutputStreamWriter output = new FileWriter("G:\\MyProjects\\Java\\java_utils_learning\\src\\main\\java\\org\\io\\nio\\target.txt")) {
            output.write("我是浪浪山上的一直野猪精");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 缓冲流测试复制PDF文件
     * 使用缓冲流复制PDF文件总耗时:2658 毫秒
     * 使用普通流复制PDF文件总耗时:341304 毫秒
     * 字节流和字节缓冲流的性能差别主要体现在
     * 我们使用两者的时候都是调用 write(int b) 和 read() 这两个一次只读取一个字节的方法的时候。
     * 由于字节缓冲流内部有缓冲区（字节数组），因此，字节缓冲流会先将读取到的字节存放在缓存区，大幅减少 IO 次数，提高读取效率。
     */
    @Test
    public void copy_pdf_to_another_pdf_buffer_stream() {
        // 记录开始时间
        long start = System.currentTimeMillis();
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("G:\\MyProjects\\Java\\testIo\\西法的刷题秘籍-2021-04-20.pdf"));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("G:\\MyProjects\\Java\\testIo\\西法的刷题秘籍-2021-04-20-副本.pdf"))) {
            int content;
            while ((content = bis.read()) != -1) {
                bos.write(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 记录结束时间
        long end = System.currentTimeMillis();
        System.out.println("使用缓冲流复制PDF文件总耗时:" + (end - start) + " 毫秒");
    }

    /**
     * 使用缓冲流复制PDF文件总耗时:2658 毫秒
     * 使用普通流复制PDF文件总耗时:341304 毫秒
     */
    @Test
    public void copy_pdf_to_another_pdf_stream() {
        // 记录开始时间
        long start = System.currentTimeMillis();
        try (FileInputStream fis = new FileInputStream("G:\\MyProjects\\Java\\testIo\\西法的刷题秘籍-2021-04-20.pdf");
             FileOutputStream fos = new FileOutputStream("G:\\MyProjects\\Java\\testIo\\西法的刷题秘籍-2021-04-20-副本.pdf")) {
            int content;
            while ((content = fis.read()) != -1) {
                fos.write(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 记录结束时间
        long end = System.currentTimeMillis();
        System.out.println("使用普通流复制PDF文件总耗时:" + (end - start) + " 毫秒");
    }

    /**
     * 如果是调用 read(byte b[]) 和 write(byte b[], int off, int len) 这两个写入一个字节数组的方法的话，
     * 只要字节数组的大小合适，两者的性能差距其实不大，基本可以忽略.因为BufferedInputStream 内部维护了一个缓冲区，这个缓冲区实际就是一个字节数组。
     * 如果直接用字节流 存到一个字节数组里面，那其实和缓冲字节流一样了
     * 使用缓冲流复制PDF文件总耗时:154 毫秒
     * 使用普通流复制PDF文件总耗时:217 毫秒
     */
    @Test
    public void copy_pdf_to_another_pdf_with_byte_array_buffer_stream() {
        // 记录开始时间
        long start = System.currentTimeMillis();
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("G:\\MyProjects\\Java\\testIo\\西法的刷题秘籍-2021-04-20.pdf"));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("G:\\MyProjects\\Java\\testIo\\西法的刷题秘籍-2021-04-20-副本.pdf"))) {
            int len;
            byte[] bytes = new byte[4 * 1024];
            //BufferedInputStream 从源头（通常是文件）读取数据（字节信息）到内存的过程中不会一个字节一个字节的读取，
            // 而是会先将读取到的字节存放在缓存区，并从内部缓冲区中单独读取字节。这样大幅减少了 IO 次数，提高了读取效率。
            while ((len = bis.read(bytes)) != -1) {
                //BufferedOutputStream 将数据（字节信息）写入到目的地（通常是文件）的过程中不会一个字节一个字节的写入，
                // 而是会先将要写入的字节存放在缓存区，并从内部缓冲区中单独写入字节。这样大幅减少了 IO 次数，提高了写入效率
                bos.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 记录结束时间
        long end = System.currentTimeMillis();
        System.out.println("使用缓冲流复制PDF文件总耗时:" + (end - start) + " 毫秒");
    }

    @Test
    public void copy_pdf_to_another_pdf_with_byte_array_stream() {
        // 记录开始时间
        long start = System.currentTimeMillis();
        try (FileInputStream fis = new FileInputStream("G:\\MyProjects\\Java\\testIo\\西法的刷题秘籍-2021-04-20.pdf");
             FileOutputStream fos = new FileOutputStream("G:\\MyProjects\\Java\\testIo\\西法的刷题秘籍-2021-04-20-副本.pdf")) {
            int len;
            byte[] bytes = new byte[4 * 1024];
            while ((len = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 记录结束时间
        long end = System.currentTimeMillis();
        System.out.println("使用普通流复制PDF文件总耗时:" + (end - start) + " 毫秒");
    }
}
