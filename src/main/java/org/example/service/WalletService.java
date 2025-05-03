package org.example.service;

import org.example.model.User;
import org.example.model.Wallet;

import java.util.List;

public interface WalletService {
    Wallet create(User user);

    Wallet findById(int id);

    List<Wallet> getAllByUserId(int userId);

    void deposit(int accountId, int moneyToDeposit);

    void withdraw(int accountId, int amountToWithdraw);

    void transfer(int fromAccountId, int toAccountId, int amountToTransfer);

    Wallet close(int accountId);
}
