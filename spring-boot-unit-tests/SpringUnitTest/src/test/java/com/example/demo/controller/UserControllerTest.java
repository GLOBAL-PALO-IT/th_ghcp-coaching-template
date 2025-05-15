package com.example.demo.controller;

import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void UserController_CreateUser_ReturnsUsers() throws Exception {
        // Create CreateUserDto instead of UserDto
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setName("John Doe");
        createUserDto.setEmail("sddf@sd.sdf");
        createUserDto.setGender("M");
        createUserDto.setAge(25);

        // Create expected response UserDto
        UserDto expectedUserDto = new UserDto();
        expectedUserDto.setName(createUserDto.getName());
        expectedUserDto.setEmail(createUserDto.getEmail());
        expectedUserDto.setGender(createUserDto.getGender());
        expectedUserDto.setAge(createUserDto.getAge());

        // Mock the service behavior to return expectedUserDto
        when(userService.createUser(ArgumentMatchers.any())).thenReturn(expectedUserDto);

        ResultActions response = mockMvc.perform(post("/api/v1/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createUserDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(expectedUserDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(expectedUserDto.getEmail())));
    }

    @Test
    void UserController_CreateUser_WithInvalidAge_ReturnsBadRequest() throws Exception {
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setName("John Doe");
        createUserDto.setEmail("test@test.com");
        createUserDto.setGender("M");
        createUserDto.setAge(17); // Invalid age


        ResultActions response = mockMvc.perform(post("/api/v1/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createUserDto)));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void UserController_CreateUser_WithInvalidEmail_ReturnsBadRequest() throws Exception {
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setName("John Doe");
        createUserDto.setEmail("invalid-email"); // Invalid email
        createUserDto.setGender("M");
        createUserDto.setAge(25);

        ResultActions response = mockMvc.perform(post("/api/v1/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createUserDto)));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void UserController_CreateUser_WithInvalidGender_ReturnsBadRequest() throws Exception {
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setName("John Doe");
        createUserDto.setEmail("test@test.com");
        createUserDto.setGender("X"); // Invalid gender
        createUserDto.setAge(25);

        ResultActions response = mockMvc.perform(post("/api/v1/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createUserDto)));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void UserController_CreateUser_WithExistingEmail_ReturnsConflict() throws Exception {
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setName("John Doe");
        createUserDto.setEmail("existing@test.com");
        createUserDto.setGender("M");
        createUserDto.setAge(25);

        when(userService.createUser(ArgumentMatchers.any()))
                .thenThrow(new IllegalArgumentException("User with email existing@test.com already exists"));

        ResultActions response = mockMvc.perform(post("/api/v1/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createUserDto)));

        response.andExpect(MockMvcResultMatchers.status().isConflict());
    }
}