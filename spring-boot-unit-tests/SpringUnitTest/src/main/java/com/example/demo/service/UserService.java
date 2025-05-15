package com.example.demo.service;

import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> {
                    UserDto userDto = new UserDto();
                    userDto.setName(user.getName());
                    userDto.setEmail(user.getEmail());
                    userDto.setGender(user.getGender());
                    userDto.setAge(user.getAge());
                    return userDto;
                })
                .toList();
    }


    public UserDto createUser(CreateUserDto createUserDto) {
        // Business logic validation
        validateUserData(createUserDto);

        // Check if user with same email exists
        Optional<User> existingUser = userRepository.findByEmail(createUserDto.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("User with email " + createUserDto.getEmail() + " already exists");
        }

        // Convert DTO to entity
        User user = new User();
        user.setName(createUserDto.getName());
        user.setEmail(createUserDto.getEmail());
        user.setGender(createUserDto.getGender());
        user.setAge(createUserDto.getAge());
        userRepository.save(user);
        // Convert entity back to DTO
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setGender(user.getGender());
        userDto.setAge(user.getAge());
        return userDto;
    }

    private void validateUserData(CreateUserDto dto) {
        if (dto.getAge() < 18) {
            throw new IllegalArgumentException("User must be at least 18 years old");
        }

        if (!dto.getGender().equalsIgnoreCase("M") &&
                !dto.getGender().equalsIgnoreCase("F")) {
            throw new IllegalArgumentException("Invalid gender value");
        }

        if (dto.getName().trim().length() < 2) {
            throw new IllegalArgumentException("Name must be at least 2 characters long");
        }
    }

    // Find a user by ID
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    // Find all users
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // Delete a user by ID
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
