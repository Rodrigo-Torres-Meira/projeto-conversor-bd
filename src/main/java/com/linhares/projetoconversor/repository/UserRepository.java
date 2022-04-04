package com.linhares.projetoconversor.repository;

import com.linhares.projetoconversor.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> getByName(String nome);
}
