package org.example.operations.processors;

import org.example.service.WalletService;
import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class WalletWithdrawProcessor implements OperationCommandProcessor {
    private final Scanner scanner;
    private final WalletService walletService;

    public WalletWithdrawProcessor(Scanner scanner, WalletService walletService) {
        this.scanner = scanner;
        this.walletService = walletService;
    }

    @Override
    public void processOperation() {
        System.out.println("Enter wallet id:");
        int walletId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter amount to withdraw:");
        int amountToWithdraw = Integer.parseInt(scanner.nextLine());
        walletService.withdraw(walletId, amountToWithdraw);
        System.out.printf("Successfully withdraw amount:%s from wallet id:%s%n", amountToWithdraw, walletId);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.WALLET_WITHDRAW;
    }
}
