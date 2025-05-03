package org.example.operations.processors;

import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;
import org.example.model.User;
import org.example.service.UserService;

import java.util.Scanner;

public class UserCreateProcessor implements OperationCommandProcessor {
    private final Scanner scanner;
    private final UserService userService;

    public UserCreateProcessor(Scanner scanner, UserService userService) {
        this.scanner = scanner;
        this.userService = userService;
    }

    @Override
    public void processOperation() {
        System.out.println("Enter login for new user:");
        String login = scanner.nextLine();
        User user = userService.create(login);
        System.out.printf("User created: %s%n", user.toString());
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.USER_CREATE;
    }
}
