package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals; // ✅ Correct import for JUnit 5

import org.junit.jupiter.api.Test; // ✅ Use JUnit 5
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTests {

    @Autowired
    private TestRestTemplate restTemplate; // Replaces TestRestTemplate in Spring Boot 3.3+

    @Test
    public void testGetUserById() {
    	String response = restTemplate.getForObject("/hello", String.class);
        assertEquals("Hello World", response);
    }
}