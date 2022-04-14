package com.linhares.controllers;

import com.linhares.DTOs.VerificationDTO;
import com.linhares.entities.User;
import com.linhares.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/linhares_api")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/user")
    public ResponseEntity<?> createUser(@RequestParam String name, @RequestParam String senha, @RequestParam String seed, @RequestParam int iterations) {

        User user = new User(name, senha, seed, iterations);

        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "userDelete/{name}")
    public ResponseEntity<?> removeStudent(@PathVariable ("name") String name){

        Optional<User> toBeDeletedStudent = userService.getUserByName(name);
        if (toBeDeletedStudent.isPresent()) {
            userService.removeUser(toBeDeletedStudent.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "test_pwd/")
    public ResponseEntity<?> testPwd(@RequestParam String name, @RequestParam String senha, @RequestParam String hostname, @RequestParam String ip_addres){

        Optional<User> userOptional = userService.getUserByName(name);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.addLog("Tentativa de Login em: " + LocalDateTime.now());
            userService.saveUser(user);
            if (Objects.equals(user.getSenha(), senha)) {
                user.addLog("Login realizado em: " + LocalDateTime.now() + "\nPor:" +
                        "\nHostname: " + hostname +
                        "\nIP Address: " + ip_addres);
                userService.saveUser(user);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "get_seed/")
    public ResponseEntity<?> getSaltIterations(@RequestParam String name) {
        Optional<User> userOptional = userService.getUserByName(name);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String seed = user.getSeed();
            int iterations = user.getIterations();
            return new ResponseEntity<>(new VerificationDTO(seed, iterations), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "get_users/")
    public ResponseEntity<?> get_users(){

        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);

    }

    @GetMapping(value = "wake_up/")
    public ResponseEntity<?> wake_up(){

        return new ResponseEntity<>(HttpStatus.OK);

    }
}

