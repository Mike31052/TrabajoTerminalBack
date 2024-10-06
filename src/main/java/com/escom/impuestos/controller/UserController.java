package com.escom.impuestos.controller;

import org.springframework.web.bind.annotation.RestController;

import com.escom.impuestos.model.UserEntity;
import com.escom.impuestos.service.UserService;

import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/getUser")
    public ResponseEntity<?> getUser(@RequestBody UserEntity loginRequest) {
    Optional<UserEntity> user = userService.getUserByEmailAndPassword(loginRequest.getCorreo(), loginRequest.getContraseña());

    if (user.isPresent()) {
        return ResponseEntity.ok(Map.of("success", true, "user", user.get()));
    } else {
        return ResponseEntity.ok(Map.of("success", false, "message", "Invalid email or password"));
    }
    }



    @PostMapping("/create")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        try {
            System.out.println(user.getCorreo());
            UserEntity newUser = userService.createUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}