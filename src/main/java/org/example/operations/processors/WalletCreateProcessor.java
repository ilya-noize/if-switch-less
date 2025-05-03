package org.example.operations.processors;

import org.example.service.WalletService;
import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;
import org.example.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class WalletCreateProcessor implements OperationCommandProcessor {
    private final Scanner scanner;
    private final UserService userService;
    private final WalletService walletService;

    public WalletCreateProcessor(Scanner scanner, UserService userService, WalletService walletService) {
        this.scanner = scanner;
        this.userService = userService;
        this.walletService = walletService;
    }

    @Override
    public void processOperation() {
        System.out.println("Enter the userId for which to create an wallet:");
        int userId = Integer.parseInt(scanner.nextLine());
        var user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("No such User with id:%s"
                        .formatted(userId)));
        var wallet = walletService.create(user);
        user.getWallets().add(wallet);

        System.out.printf("New wallet created with id: %s for user: %s%n",
                wallet.getId(), user.getLogin());

    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.WALLET_CREATE;
    }
}
