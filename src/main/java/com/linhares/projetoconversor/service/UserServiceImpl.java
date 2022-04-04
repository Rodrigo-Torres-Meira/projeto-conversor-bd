package com.linhares.projetoconversor.service;

import com.linhares.projetoconversor.entities.User;
import com.linhares.projetoconversor.repository.UserRepository;
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
