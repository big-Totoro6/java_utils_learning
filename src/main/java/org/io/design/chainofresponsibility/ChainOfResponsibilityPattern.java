package org.io.design.chainofresponsibility;

/**顾名思义，责任链模式（Chain of Responsibility Pattern）为请求创建了一个接收者对象的链。这种模式给予请求的类型，对请求的发送者和接收者进行解耦。这种类型的设计模式属于行为型模式。
 * 意图：避免请求发送者与接收者耦合在一起，让多个对象都有可能接收请求，将这些对象连接成一条链，并且沿着这条链传递请求，直到有对象处理它为止。
 *
 * 我们创建抽象类 AbstractLogger，带有详细的日志记录级别。然后我们创建三种类型的记录器，都扩展了 AbstractLogger。
 * 每个记录器消息的级别是否属于自己的级别，如果是则相应地打印出来，否则将不打印并把消息传给下一个记录器。
 */
public class ChainOfResponsibilityPattern {
    private static AbstractLogger getChainOfLoggers(){
        //有点像链表的玩法 这样头就是 error 后面就是 file 再后面就是console
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);//level 3
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);//level 2
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);//level 1
        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);
        return errorLogger;
    }

    public static void main(String[] args) {
        //创建不同类型的记录器。赋予它们不同的错误级别，并在每个记录器中设置下一个记录器。每个记录器中的下一个记录器代表的是链的一部分。
        AbstractLogger loggerChain = getChainOfLoggers();

        //Standard Console::Logger: This is an information.
        loggerChain.logMessage(AbstractLogger.INFO, "This is an information.");

        //File::Logger: This is a debug level information.
        loggerChain.logMessage(AbstractLogger.DEBUG,
                "This is a debug level information.");

        //Error Console::Logger: This is an error information.
        loggerChain.logMessage(AbstractLogger.ERROR,
                "This is an error information.");
    }
}
