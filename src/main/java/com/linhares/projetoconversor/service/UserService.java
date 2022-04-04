package com.linhares.projetoconversor.service;

import com.linhares.projetoconversor.entities.User;

import java.util.Optional;

public interface UserService {
    void saveUser(User user);
    void removeUser(User user);
    Optional<User> getUserByName(String name);
}
