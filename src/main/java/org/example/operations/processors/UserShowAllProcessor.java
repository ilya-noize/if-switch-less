package org.example.operations.processors;

import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;
import org.example.model.User;
import org.example.service.UserService;

import java.util.List;

public class UserShowAllProcessor implements OperationCommandProcessor {

    private final UserService userService;

    public UserShowAllProcessor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void processOperation() {

        List<User> users = userService.getAll();
        System.out.println("List of all users");
        users.forEach(System.out::println);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.SHOW_ALL_USERS;
    }
}
