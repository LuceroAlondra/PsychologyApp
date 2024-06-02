package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gender;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    @Column(unique = true) // Asegura que el correo electrónico sea único en la base de datos
    private String email;

    @NotBlank
    private String password;

    private String username;

    // Campos de auditoría
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User() {
        // Inicializar campos de auditoría al crear un nuevo usuario
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Constructor para crear un usuario a partir de un objeto RandomUser
    public User(RandomUser randomUser) {
        this();
        this.gender = randomUser.getGender();
        this.firstName = randomUser.getName().getFirst();
        this.lastName = randomUser.getName().getLast();
        this.email = randomUser.getEmail();
        this.username = randomUser.getLogin().getUsername();
        this.password = randomUser.getLogin().getPassword();
    }

    // Getters y setters

    // Métodos para actualizar la marca de tiempo de la última actualización
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}