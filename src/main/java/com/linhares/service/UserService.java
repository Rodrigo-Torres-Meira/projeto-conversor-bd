package com.linhares.service;

import com.linhares.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(User user);
    void removeUser(User user);
    Optional<User> getUserByName(String name);
    List<User> getAllUsers();
}
