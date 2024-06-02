package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.RandomUserService;

import jakarta.transaction.Transactional;

@RestController
public class RandomUserController {

    private final RandomUserService randomUserService;

    @Autowired
    public RandomUserController(RandomUserService randomUserService) {
        this.randomUserService = randomUserService;
    }
    
    @Transactional
    @GetMapping("/save-random-user")
    public void saveRandomUserToDatabase() {
        randomUserService.saveRandomUserToDatabase();
    }

  
}
