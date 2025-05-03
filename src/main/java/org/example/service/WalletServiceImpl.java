package org.example.service;

import org.example.model.User;
import org.example.model.Wallet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WalletServiceImpl implements WalletService {
    private final int defaultAccountAmount;
    private final double transferCommission;
    private final Map<Integer, Wallet> accounts = new HashMap<>();
    private int idCounter = 0;

    public WalletServiceImpl(int defaultAccountAmount, double transferCommission) {
        this.defaultAccountAmount = defaultAccountAmount;
        this.transferCommission = transferCommission;
    }

    @Override
    public Wallet create(User user) {
        idCounter++;
        Wallet wallet = new Wallet(idCounter, user.getId(), defaultAccountAmount);
        accounts.put(wallet.getId(), wallet);
        return wallet;
    }

    @Override
    public Wallet findById(int id) {
        Optional<Wallet> wallet = Optional.ofNullable(accounts.get(id));

        return wallet.orElseThrow(() -> new IllegalArgumentException(
                "No such wallet: id=%s".formatted(id)));
    }

    @Override
    public List<Wallet> getAllByUserId(int userId) {
        List<Wallet> list = new ArrayList<>();
        for (Wallet wallet : accounts.values()) {
            if (wallet.getUserId() == userId) {
                list.add(wallet);
            }
        }
        return list;
    }

    @Override
    public void deposit(int accountId, int moneyToDeposit) {
        var account = findById(accountId);
        if (moneyToDeposit <= 0) {
            throw new IllegalArgumentException("Can't deposit not positive amount: amount=%s"
                    .formatted(moneyToDeposit));
        }
        account.depositingMoney(moneyToDeposit);
    }

    @Override
    public void withdraw(int accountId, int amountToWithdraw) {
        var account = findById(accountId);
        amountIsPositive(amountToWithdraw);
        amountNotMoreBalance(amountToWithdraw, account);
        account.debitingMoney(amountToWithdraw);
    }

    @Override
    public void transfer(int fromAccountId, int toAccountId, int amountToTransfer) {
        amountIsPositive(amountToTransfer);
        Wallet fromWallet = findById(fromAccountId);
        Wallet toWallet = findById(toAccountId);
        amountNotMoreBalance(amountToTransfer, fromWallet);
        int totalAmountToDeposit = toWallet.getUserId() == fromWallet.getUserId()
                ? amountToTransfer
                : (int) (amountToTransfer * (1 - transferCommission));
        fromWallet.debitingMoney(amountToTransfer);
        toWallet.depositingMoney(totalAmountToDeposit);
    }

    @Override
    public Wallet close(int accountId) {
        Wallet walletToRemove = findById(accountId);
        List<Wallet> accountsByUser = getAllByUserId(walletToRemove.getUserId());
        if (accountsByUser.size() == 1) {
            throw new IllegalArgumentException("Can't close the only one account");
        }
        Wallet walletToDeposit = accountsByUser.stream()
                .filter(it -> it.getId() != accountId).findFirst().orElseThrow();
        walletToDeposit.depositingMoney(walletToRemove.getMoney());
        accounts.remove(accountId);
        return walletToRemove;
    }

    private void amountIsPositive(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Only positive amount: amount=%s"
                    .formatted(amount));
        }
    }

    private void amountNotMoreBalance(int amount, Wallet wallet) {
        if (wallet.getMoney() < amount) {
            throw new IllegalArgumentException("The amount can't exceed the account: id=%s, money=%s, amount%s"
                    .formatted(wallet.getId(), wallet.getMoney(), amount));
        }
    }
}
