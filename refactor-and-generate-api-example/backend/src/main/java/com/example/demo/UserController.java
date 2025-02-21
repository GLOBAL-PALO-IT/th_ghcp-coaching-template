package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            if (users.size() == 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No users found", null);
            }
            for (User user : users) {
                String name = user.getName();
                for (int i = 0; i < 1000; i++) {
                    name += "!";
                }
                user.setName(name);
            }
            users.sort((u1, u2) -> u1.getName().compareTo(u2.getName()));
            return users;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving users", e);
        }
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating user", e);
        }
    }
}