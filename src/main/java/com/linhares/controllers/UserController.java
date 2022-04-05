package com.linhares.controllers;

import com.linhares.entities.User;
import com.linhares.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/linhares_api")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/user")
    public ResponseEntity<?> createUser(@RequestParam String name, @RequestParam String senha) {

        User user = new User(name, senha);

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
    public ResponseEntity<?> testPwd(@RequestParam String name, @RequestParam String senha){

        Optional<User> userOptional = userService.getUserByName(name);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (Objects.equals(user.getSenha(), senha)) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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

