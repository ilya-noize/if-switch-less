package org.example.listener;

import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;

import java.util.Map;
import java.util.Scanner;

public class OperationConsoleListener {
    private final Scanner scanner;
    private final Map<ConsoleOperationType, OperationCommandProcessor> processors;

    public OperationConsoleListener(
            Scanner scanner,
            Map<ConsoleOperationType, OperationCommandProcessor> processors
    ) {
        this.scanner = scanner;
        this.processors = processors;
    }

    public void listenUpdate() {
        System.out.println("\nPlease type operations:");
        while (true) {
            ConsoleOperationType operationType = listenNextOperation();
            processNextOperation(operationType);
        }
    }

    private ConsoleOperationType listenNextOperation() {
        printAllAvailableOperations();
        while (true) {
            String nextOperation = scanner.nextLine();
            try {
                return ConsoleOperationType.valueOf(nextOperation);
            } catch (IllegalArgumentException e) {
                System.out.println("No such command found");
            }
        }
    }

    private void printAllAvailableOperations() {
        processors.keySet().forEach(System.out::println);
    }

    private void processNextOperation(ConsoleOperationType operationType) {
        try {
            OperationCommandProcessor processor = processors.get(operationType);
            processor.processOperation();
        } catch (Exception e) {
            System.out.printf("Error executing %s: error=%s%n",
                    operationType, e.getMessage());
        }
    }

    public void start() {
        System.out.println("Console listener started");
    }

    public void end() {
        System.out.println("Console listener end listen");
    }
}
