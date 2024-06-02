package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.RandomUser;
import com.example.demo.model.RandomUserResponse;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class RandomUserService {

    private final RestTemplate restTemplate;
    private final UserRepository userRepository;

    private final String API_URL = "https://randomuser.me/api/";

    @Autowired
    public RandomUserService(RestTemplate restTemplate, UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveRandomUserToDatabase() {
        ResponseEntity<RandomUserResponse> responseEntity = restTemplate.getForEntity(API_URL, RandomUserResponse.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            RandomUserResponse response = responseEntity.getBody();
            if (response != null && response.getResults() != null && response.getResults().length > 0) {
                RandomUser randomUser = response.getResults()[0];
                User user = new User(randomUser);
                userRepository.save(user);
            }
        }
    }

}

