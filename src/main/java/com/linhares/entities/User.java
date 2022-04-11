package com.linhares.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class User {
    @Id
    @Column(name = "nome", nullable = false, length = 32)
    private String name;

    private String senha;

    private String seed;

    private int iterations;

    public User() {

    }

    public User(String name, String senha, String seed, int iterations) {
        this.name = name;
        this.senha = senha;
        this.seed = seed;
        this.iterations = iterations;
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

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }
}
