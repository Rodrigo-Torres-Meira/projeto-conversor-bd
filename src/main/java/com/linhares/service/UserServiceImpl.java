package com.linhares.service;

import com.linhares.entities.User;
import com.linhares.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void removeUser(User user) {
        userRepository.delete(user);

    }

    @Override
    public Optional<User> getUserByName(String name) {
        return userRepository.getByName(name);
    }
}