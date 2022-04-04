package com.linhares.projetoconversor.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class User {
    @Id
    @Column(name = "nome", nullable = false, length = 32)
    private String name;

    private String senha;

    public User(String name, String senha) {
        this.name = name;
        this.senha = senha;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public String getSenha() {
        return senha;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
