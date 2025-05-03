package org.example.operations.processors;

import org.example.model.Wallet;
import org.example.service.WalletService;
import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class WalletCloseProcessor implements OperationCommandProcessor {
    private final Scanner scanner;
    private final UserService userService;
    private final WalletService walletService;

    public WalletCloseProcessor(Scanner scanner, UserService userService, WalletService walletService) {
        this.scanner = scanner;
        this.walletService = walletService;
        this.userService = userService;
    }

    @Override
    public void processOperation() {
        System.out.println("Enter wallet id to close:");
        int walletId = Integer.parseInt(scanner.nextLine());
        Wallet wallet = walletService.close(walletId);
        User user = userService.findById(wallet.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("No such user with id:%s"
                        .formatted(wallet.getUserId())));
        user.getWallets().remove(wallet);
        System.out.printf("Wallet successfully closed with id:%s from user:%s%n", walletId, user.getLogin());
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.WALLET_CLOSE;
    }
}
