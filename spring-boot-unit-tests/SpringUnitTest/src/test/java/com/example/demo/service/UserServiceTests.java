package com.example.demo.service;

import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Enables Mockito extension
public class UserServiceTests {

    @Mock // Mocks the UserRepository
    private UserRepository userRepository;

    @InjectMocks // Injects the mocks into the UserService instance
    private UserService userService;


    @Test
    public void UserService_CreateUser_ReturnsUser() {
        User fakeUser = new User();
        fakeUser.setName("John Doe");
        fakeUser.setEmail("eef@sfs.fd");
        fakeUser.setGender("M");
        fakeUser.setAge(25);
        CreateUserDto createFakeUserDto = new CreateUserDto();
        createFakeUserDto.setName("John Doe");
        createFakeUserDto.setEmail("eef@sfs.fd");
        createFakeUserDto.setGender("M");
        createFakeUserDto.setAge(25);
        // Mock the repository behavior
        when(userRepository.findByEmail(fakeUser.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(fakeUser);

        UserDto savedFakeUser = userService.createUser(createFakeUserDto);

        Assertions.assertThat(savedFakeUser).isNotNull();
        Assertions.assertThat(savedFakeUser.getName()).isEqualTo("John Doe");
        Assertions.assertThat(savedFakeUser.getEmail()).isEqualTo("eef@sfs.fd");
        Assertions.assertThat(savedFakeUser.getGender()).isEqualTo("M");
        Assertions.assertThat(savedFakeUser.getAge()).isEqualTo(25);

    }

    @Test
    public void UserService_CreateUser_WithInvalidAge_ThrowsException() {
        CreateUserDto createFakeUserDto = new CreateUserDto();
        createFakeUserDto.setName("John Doe");
        createFakeUserDto.setEmail("eef@sfs.fd");
        createFakeUserDto.setGender("M");
        createFakeUserDto.setAge(17);

        Assertions
                .assertThatThrownBy(() -> userService.createUser(createFakeUserDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("User must be at least 18 years old");
    }

    @Test
    public void UserService_CreateUser_WithExistingEmail_ThrowsException() {
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setName("John Doe");
        createUserDto.setEmail("existing@email.com");
        createUserDto.setGender("M");
        createUserDto.setAge(25);

        User existingUser = new User();
        existingUser.setEmail("existing@email.com");

        when(userRepository.findByEmail("existing@email.com")).thenReturn(Optional.of(existingUser));

        Assertions
                .assertThatThrownBy(() -> userService.createUser(createUserDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("User with email existing@email.com already exists");
    }

    @Test
    public void UserService_CreateUser_WithInvalidGender_ThrowsException() {
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setName("John Doe");
        createUserDto.setEmail("john@email.com");
        createUserDto.setGender("X");
        createUserDto.setAge(25);

        Assertions
                .assertThatThrownBy(() -> userService.createUser(createUserDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid gender value");
    }

    @Test
    public void UserService_CreateUser_WithShortName_ThrowsException() {
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setName("J");
        createUserDto.setEmail("john@email.com");
        createUserDto.setGender("M");
        createUserDto.setAge(25);

        Assertions
                .assertThatThrownBy(() -> userService.createUser(createUserDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Name must be at least 2 characters long");
    }

    @Test
    public void UserService_FindUserById_ReturnsUser() {
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john@email.com");
        user.setGender("M");
        user.setAge(25);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findUserById(1L);

        Assertions.assertThat(foundUser).isPresent();
        Assertions.assertThat(foundUser.get().getId()).isEqualTo(1L);
        Assertions.assertThat(foundUser.get().getName()).isEqualTo("John Doe");
    }
}
