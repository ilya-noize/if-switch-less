package org.example.listener;

import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class OperationConsoleListener {
    private final Scanner scanner;
    private final Map<ConsoleOperationType, OperationCommandProcessor> processors;

    public OperationConsoleListener(
            Scanner scanner,
            List<OperationCommandProcessor> commandProcessors
    ) {
        this.scanner = scanner;
        this.processors = commandProcessors
                .stream()
                .collect(Collectors.toMap(
                                OperationCommandProcessor::getOperationType,
                                processor -> processor
                        )
                );
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
