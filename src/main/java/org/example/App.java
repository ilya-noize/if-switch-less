package org.example;

import org.example.listener.OperationConsoleListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ilya-noize
 * @version 1.0
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.example");
        OperationConsoleListener consoleListener = context.getBean(OperationConsoleListener.class);
        consoleListener.start();
        consoleListener.listenUpdate();
        consoleListener.end();
    }
}
