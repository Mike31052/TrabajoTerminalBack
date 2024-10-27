package com.escom.impuestos.service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.escom.impuestos.model.UserEntity;
import com.escom.impuestos.model.VerificationTokenEntity;
import com.escom.impuestos.repository.UserRepository;
import com.escom.impuestos.repository.VerificationTokenRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;  // Servicio para enviar el correo

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public Optional<UserEntity> getUserByEmailAndPassword(String correo, String contraseña) {
        return userRepository.findByCorreoAndContraseña(correo, contraseña);
    }

    public UserEntity createUser(UserEntity user) {
        // Verificar si ya existe un usuario con el mismo correo
        Optional<UserEntity> existingUser = userRepository.findByCorreo(user.getCorreo());

        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }

        // Si el correo no existe, crear el nuevo usuario
        user.setId(UUID.randomUUID().toString()); // Generar y asignar un ID único
        user.setFecha_creacion(new Date());
        user.setEnable(false);  // El usuario no está habilitado hasta que verifique su correo

        UserEntity savedUser = userRepository.save(user);

        // Generar token de verificación
        String token = UUID.randomUUID().toString();
        VerificationTokenEntity verificationToken = new VerificationTokenEntity(token, savedUser);
        tokenRepository.save(verificationToken);

        // Enviar correo de verificación
        String verificationUrl = "http://localhost:4200/validateToken?token=" + token;
        try {
            emailService.sendVerificationEmail(user.getCorreo(), verificationUrl);
        } catch (IllegalStateException e) {
            // Si ocurre un error al enviar el correo, eliminar el usuario creado
            userRepository.delete(savedUser); // Rollback manual del usuario guardado
            tokenRepository.delete(verificationToken); // Eliminar token de verificación
            throw new IllegalStateException("Error al enviar el correo. El usuario no fue creado.", e);
        }

        return savedUser;
    }

    public boolean verifyUser(String token) {
        VerificationTokenEntity verificationToken = tokenRepository.findByToken(token);

        if (verificationToken == null || verificationToken.isExpired()) {
            return false;
        }

        // Habilitar al usuario
        UserEntity user = verificationToken.getUser();
        user.setEnable(true);
        userRepository.save(user);

        // Eliminar el token una vez que ha sido usado
        tokenRepository.delete(verificationToken);

        return true;
    }

    public boolean verifyToken(String token) {
        VerificationTokenEntity verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null || verificationToken.isExpired()) {
            return false;
        }
        return true;
    }

    public boolean searchEmail(String correo){
        logger.info("correo {}:",correo);
        Optional<UserEntity>  userEntityOptional = userRepository.findByCorreo(correo);
        if(!userEntityOptional.isPresent()){
            return false;
        }
        // Extraer el valor del Optional
        UserEntity userEntity = userEntityOptional.get();

        // Generar token de verificación
        String token = UUID.randomUUID().toString();
        VerificationTokenEntity verificationToken = new VerificationTokenEntity(token, userEntity);
        tokenRepository.save(verificationToken);

        // Enviar correo de verificación
        String verificationUrl = "http://localhost:4200/changePassword?token=" + token;
        try {
            emailService.sendVerificationEmail(userEntity.getCorreo(), verificationUrl);
        } catch (IllegalStateException e) {
            tokenRepository.delete(verificationToken); // Eliminar token de verificación
            throw new IllegalStateException("Error al enviar el correo. El usuario no fue creado.", e);
        }


        return true;
    }

    public boolean updatePassword(String token, String password) {
        VerificationTokenEntity verificationToken = tokenRepository.findByToken(token);

        if (verificationToken == null || verificationToken.isExpired()) {
            return false;
        }

        // Actualizar contraseña
        UserEntity user = verificationToken.getUser();
        user.setContraseña(password);
        userRepository.save(user);

        // Eliminar el token una vez que ha sido usado
        tokenRepository.delete(verificationToken);

        return true;
    }

    @Transactional
    public UserEntity actualizarRegimen(UserEntity user) {
        // Buscar al usuario en la base de datos para verificar si existe
        Optional<UserEntity> userExist = userRepository.findById(user.getId());
        if (userExist.isPresent()) {
            // Si el usuario existe, actualizar sus datos, incluido el régimen
            UserEntity usuarioActualizado = userExist.get();
            usuarioActualizado.setRegimen(user.getRegimen());
            // Aquí puedes actualizar otros atributos si lo necesitas
            return userRepository.save(usuarioActualizado);  // Guardar cambios
        } else {
            throw new EntityNotFoundException("Usuario no encontrado");
        }
    }
}
