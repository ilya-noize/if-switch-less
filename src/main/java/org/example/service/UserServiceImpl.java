package org.example.service;

import org.example.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class UserServiceImpl implements UserService {
    private final Map<Integer, User> users = new HashMap<>();
    private final Set<String> takenLogins = new HashSet<>();
    private int idCounter = 0;
    private final WalletService walletService;

    public UserServiceImpl(WalletService walletService) {
        this.walletService = walletService;
    }

    @Override
    public User create(String login) {
        if (takenLogins.contains(login)) {
            throw new IllegalArgumentException("User already exists with login=%s".formatted(login));
        }
        takenLogins.add(login);
        idCounter++;
        var user = new User(idCounter, login, new ArrayList<>());
        var wallet = walletService.create(user);
        user.getWallets().add(wallet);
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }
}
