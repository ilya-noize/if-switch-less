package org.example.operations.processors;

import org.example.service.WalletService;
import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;

import java.util.Scanner;

public class WalletDepositProcessor implements OperationCommandProcessor {
    private final Scanner scanner;
    private final WalletService walletService;

    public WalletDepositProcessor(Scanner scanner, WalletService walletService) {
        this.scanner = scanner;
        this.walletService = walletService;
    }

    @Override
    public void processOperation() {
        System.out.println("Enter account id:");
        int accountId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter amount to deposit:");
        int amountToDeposit = Integer.parseInt(scanner.nextLine());
        walletService.deposit(accountId, amountToDeposit);
        System.out.printf("Successfully deposited amount:%s to account id:%s%n", amountToDeposit, accountId);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.WALLET_DEPOSIT;
    }
}
