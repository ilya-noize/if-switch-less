package org.example.operations.processors;

import org.example.service.WalletService;
import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;

import java.util.Scanner;

public class WalletWithdrawProcessor implements OperationCommandProcessor {
    private final Scanner scanner;
    private final WalletService walletService;

    public WalletWithdrawProcessor(Scanner scanner, WalletService walletService) {
        this.scanner = scanner;
        this.walletService = walletService;
    }

    @Override
    public void processOperation() {
        System.out.println("Enter account id:");
        int accountId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter amount to withdraw:");
        int amountToWithdraw = Integer.parseInt(scanner.nextLine());
        walletService.withdraw(accountId, amountToWithdraw);
        System.out.printf("Successfully withdraw amount:%s from account id:%s%n", amountToWithdraw, accountId);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.WALLET_WITHDRAW;
    }
}
