package com.escom.impuestos.service;

import java.util.Date;
import java.util.UUID;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escom.impuestos.model.UserEntity;
import com.escom.impuestos.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<UserEntity> getUserByEmailAndPassword(String correo, String contraseña) {
        return userRepository.findByCorreoAndContraseña(correo, contraseña);
    }

    public UserEntity createUser(UserEntity user) {
        // Verificar si ya existe un usuario con el mismo correo
        Optional<UserEntity> existingUser = userRepository.findByCorreo(user.getCorreo());
        
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("El correo ya está registrado");  // Manejo de error si el usuario existe
        }

        // Si el correo no existe, crear el nuevo usuario
        user.setId(UUID.randomUUID().toString()); // Generar y asignar un ID único
        user.setFecha_creacion(new Date());  // Asignar la fecha actual
        return userRepository.save(user);  // Guardar el nuevo usuario
    }
}
