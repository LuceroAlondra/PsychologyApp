package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        // Verificar si el correo electrónico ya está registrado
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new IllegalArgumentException("El correo electrónico ya está registrado");
        }

        // Establecer campos de auditoría
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        // Guardo el usuario en la base de datos
        return userRepository.save(user);
    }

    public boolean login(String email, String password) {
        // Buscar el usuario por correo electrónico
        User user = userRepository.findByEmail(email);
        
        // si el usuario existe y si la contraseña coincide
        if (user != null && user.getPassword().equals(password)) {
            return true; // Inicio de sesión exitoso
        } else {
            return false; // Credenciales incorrectas
        }
    }
	
}
