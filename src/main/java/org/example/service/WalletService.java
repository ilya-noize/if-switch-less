package org.example.service;

import org.example.model.User;
import org.example.model.Wallet;

import java.util.List;

public interface WalletService {
    Wallet create(User user);

    Wallet findById(int id);

    List<Wallet> getAllByUserId(int userId);

    void deposit(int walletId, int moneyToDeposit);

    void withdraw(int walletId, int amountToWithdraw);

    void transfer(int fromWalletId, int toWalletId, int amountToTransfer);

    Wallet close(int walletId);
}
