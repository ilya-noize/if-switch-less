package org.example.service;

import org.example.model.User;
import org.example.model.Wallet;
import org.example.model.WalletProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {
    private final Map<Integer, Wallet> wallets = new HashMap<>();
    private final WalletProperties walletProperties;
    private int idCounter = 0;

    public WalletServiceImpl(WalletProperties walletProperties) {
        this.walletProperties = walletProperties;
    }

    @Override
    public Wallet create(User user) {
        idCounter++;
        Wallet wallet = new Wallet(idCounter, user.getId(), walletProperties.getDefaultAmount());
        wallets.put(wallet.getId(), wallet);
        return wallet;
    }

    @Override
    public Wallet findById(int id) {
        Optional<Wallet> wallet = Optional.ofNullable(wallets.get(id));

        return wallet.orElseThrow(() -> new IllegalArgumentException(
                "No such wallet: id=%s".formatted(id)));
    }

    @Override
    public List<Wallet> getAllByUserId(int userId) {
        List<Wallet> list = new ArrayList<>();
        for (Wallet wallet : wallets.values()) {
            if (wallet.getUserId() == userId) {
                list.add(wallet);
            }
        }
        return list;
    }

    @Override
    public void deposit(int walletId, int moneyToDeposit) {
        var wallet = findById(walletId);
        if (moneyToDeposit <= 0) {
            throw new IllegalArgumentException("Can't deposit not positive amount: amount=%s"
                    .formatted(moneyToDeposit));
        }
        wallet.depositingMoney(moneyToDeposit);
    }

    @Override
    public void withdraw(int walletId, int amountToWithdraw) {
        var wallet = findById(walletId);
        amountIsPositive(amountToWithdraw);
        amountNotMoreBalance(amountToWithdraw, wallet);
        wallet.debitingMoney(amountToWithdraw);
    }

    @Override
    public void transfer(int fromWalletId, int toWalletId, int amountToTransfer) {
        amountIsPositive(amountToTransfer);
        Wallet fromWallet = findById(fromWalletId);
        Wallet toWallet = findById(toWalletId);
        amountNotMoreBalance(amountToTransfer, fromWallet);
        int totalAmountToDeposit = toWallet.getUserId() == fromWallet.getUserId()
                ? amountToTransfer
                : (int) (amountToTransfer * (1 - walletProperties.getTransferCommission()));
        fromWallet.debitingMoney(amountToTransfer);
        toWallet.depositingMoney(totalAmountToDeposit);
    }

    @Override
    public Wallet close(int walletId) {
        Wallet walletToRemove = findById(walletId);
        List<Wallet> walletsByUser = getAllByUserId(walletToRemove.getUserId());
        if (walletsByUser.size() == 1) {
            throw new IllegalArgumentException("Can't close the only one wallet");
        }
        Wallet walletToDeposit = walletsByUser.stream()
                .filter(it -> it.getId() != walletId).findFirst().orElseThrow();
        walletToDeposit.depositingMoney(walletToRemove.getMoney());
        wallets.remove(walletId);
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
            throw new IllegalArgumentException("The amount can't exceed the wallet: id=%s, money=%s, amount%s"
                    .formatted(wallet.getId(), wallet.getMoney(), amount));
        }
    }
}
