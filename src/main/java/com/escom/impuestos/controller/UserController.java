package com.escom.impuestos.controller;

import org.springframework.web.bind.annotation.RestController;

import com.escom.impuestos.model.UserEntity;
import com.escom.impuestos.service.UserService;

import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/getUser")
    public ResponseEntity<?> getUser(@RequestBody UserEntity loginRequest) {
        Optional<UserEntity> user = userService.getUserByEmailAndPassword(loginRequest.getCorreo(), loginRequest.getContraseña());

        if (user.isPresent() && user.get().isEnable()) {
            // Usuario autenticado con éxito y verificado
            return ResponseEntity.ok(Map.of("success", true, "user", user.get()));
        } else if (user.isPresent()) {
            // Usuario encontrado pero no ha verificado su correo electrónico
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                             .body(Map.of("success", false, "message", "No ha verificado su correo electrónico"));
        } else {
            // Credenciales inválidas o usuario no encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(Map.of("success", false, "message", "Correo o contraseña inválidos"));
        }
    }


    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserEntity user) {
        try {
            userService.createUser(user);
            return new ResponseEntity<>(Map.of("success", true, "message", "Usuario creado. Por favor, verifica tu correo electrónico."), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(Map.of("success", false, "message", e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (IllegalStateException e) {
            // Responder con un mensaje específico si falla el envío del correo
            return new ResponseEntity<>(Map.of("success", false, "message", "Error al enviar el correo. El usuario no fue creado."), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("success", false, "message", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/searchEmail")
    public ResponseEntity<?> searchEmail(@RequestBody Map<String, String> payload) {
        try {
            boolean isValid = userService.searchEmail(payload.get("email"));
            if(isValid){
                return ResponseEntity.ok(Map.of("success", true, "message", "El link se ha enviado a tu correo"));
            }else{
                return ResponseEntity.ok(Map.of("success", false, "message", "El correo no se pudo verificar"));
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(Map.of("success", false, "message", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestParam("token") String token) {
        boolean isVerified = userService.verifyUser(token);

        if (isVerified) {
            return ResponseEntity.ok(Map.of("success", true, "message", "Cuenta verificada exitosamente"));
        } else {
            return ResponseEntity.ok(Map.of("success", false, "message", "Token inválido o caducado"));
        }
    }

    @GetMapping("/verify-token")
    public ResponseEntity<?> verifyToken(@RequestParam("token") String token) {
        boolean isVerified = userService.verifyToken(token);

        if (isVerified) {
            return ResponseEntity.ok(Map.of("success", true, "message", "Cuenta verificada exitosamente"));
        } else {
            return ResponseEntity.ok(Map.of("success", false, "message", "Token inválido o caducado"));
        }
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestBody Map<String, String> payload) {
        boolean isVerified = userService.updatePassword(payload.get("token"), payload.get("password"));

        if (isVerified) {
            return ResponseEntity.ok(Map.of("success", true, "message", "Contraseña actualizada exitosamente"));
        } else {
            return ResponseEntity.ok(Map.of("success", false, "message", "Token inválido o caducado"));
        }
    }

    @PutMapping("/updateRegimen")
    public ResponseEntity<?> actualizarRegimen(@RequestBody UserEntity user) {
        UserEntity userUpdated = userService.actualizarRegimen(user);
        return ResponseEntity.ok(Map.of("success", true, "user", userUpdated));
    }
}
