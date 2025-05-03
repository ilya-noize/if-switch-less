package org.example.model;

import java.util.List;

public class User {
    private final int id;
    private final String login;
    private final List<Wallet> wallets;

    public User(int id, String login, List<Wallet> wallets) {
        this.id = id;
        this.login = login;
        this.wallets = wallets;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public List<Wallet> getAccounts() {
        return wallets;
    }

    @Override
    public String toString() {
        return "User{\tid=%s,\tlogin=%s,\twallets=%s%n}".formatted(id, login, wallets);
    }
}
