package org.example.operations.processors;

import org.example.service.WalletService;
import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;

import java.util.Scanner;

public class WalletTransferProcessor implements OperationCommandProcessor {
    private final Scanner scanner;
    private final WalletService walletService;

    public WalletTransferProcessor(Scanner scanner, WalletService walletService) {
        this.scanner = scanner;
        this.walletService = walletService;
    }

    @Override
    public void processOperation() {
        System.out.println("Enter source wallet id:");
        int fromWalletId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter destination wallet id:");
        int toWalletId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter amount to transfer:");
        int amountToTransfer = Integer.parseInt(scanner.nextLine());
        walletService.transfer(fromWalletId, toWalletId, amountToTransfer);
        System.out.printf(
                "Successfully transferred %s from walletId %s to walletId: %s%n",
                amountToTransfer, fromWalletId, toWalletId);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.WALLET_TRANSFER;
    }
}
