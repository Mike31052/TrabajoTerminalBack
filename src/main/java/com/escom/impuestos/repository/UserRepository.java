package com.escom.impuestos.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.data.repository.Repository;
import com.escom.impuestos.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{
    Optional<UserEntity> findByCorreoAndContraseña(String email, String password);

    Optional<UserEntity> findByCorreo(String email);  // Método para buscar usuario por correo
}
