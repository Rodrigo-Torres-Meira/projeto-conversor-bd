package com.linhares.projetoconversor.controllers;

import com.linhares.projetoconversor.entities.User;
import com.linhares.projetoconversor.service.UserService;
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

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestParam String name, @RequestParam String senha) {

        User user = new User(name, senha);

        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "userDelete/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeStudent(@PathVariable ("name") String name){

        Optional<User> toBeDeletedStudent = userService.getUserByName(name);
        if (toBeDeletedStudent.isPresent()) {
            userService.removeUser(toBeDeletedStudent.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "test_pwd/", method = RequestMethod.POST)
    public ResponseEntity<?> testPwd(@RequestParam String name, @RequestParam String senha){

        Optional<User> userOptional = userService.getUserByName(name);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (Objects.equals(user.getSenha(), senha)) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userOptional, HttpStatus.BAD_REQUEST);
    }
}

