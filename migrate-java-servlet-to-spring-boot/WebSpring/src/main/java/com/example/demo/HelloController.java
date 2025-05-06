package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.Building;
import models.Room;
import repositories.RoomRepository;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public String doGet() {
        return "Hello World";
    }

    @PostMapping
    public String doPost() {
        String message = "Success";
        try {
            Room room = new Room("Room 1", new Building("Building 1"));
            roomRepository.save(room);
        } catch (Exception ex) {
            ex.printStackTrace();
            message = ex.getMessage();
        }
        return message;
    }
}