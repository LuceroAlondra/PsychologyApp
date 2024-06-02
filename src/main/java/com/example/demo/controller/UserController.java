package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
	private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@Valid @RequestBody User user) {
        try {
            userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        try {
            //Llama al método de inicio de sesión en UserService
            boolean loggedIn = userService.login(email, password);
            if (loggedIn) {
                return ResponseEntity.ok("Inicio de sesión exitoso");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo electrónico o contraseña incorrectos");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor");
        }
    }
}
