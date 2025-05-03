package org.example;

import org.example.listener.OperationConsoleListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ilya-noize
 * @version 1.0
 */
public class App {
    private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.example");

    public App() {
    }

    public static void main(String[] args ){
        new App();
    }

    public static void shutdown() {
        context.close();
    }
}
