package org.example.service;

import org.example.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User create(String login);

    Optional<User> findById(int id);

    List<User> getAll();
}
