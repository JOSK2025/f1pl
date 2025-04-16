package com.josk.f1pp.controller;

import com.josk.f1pp.dto.LoginDto;
import com.josk.f1pp.dto.RegisterDto;
import com.josk.f1pp.model.UserEntity;
import com.josk.f1pp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
public class AuthController {


    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto request) {
        if(request.username == null || request.username.isEmpty()) {
            return ResponseEntity.badRequest().body("Podaj nazwę użytkownika!");
        }
        if(request.email == null || request.email.isEmpty()) {
            return ResponseEntity.badRequest().body("Podaj adres email!");
        }
        if(request.password == null || request.password.isEmpty()) {
            return ResponseEntity.badRequest().body("Podaj swoje hasło!");
        }
        Optional<UserEntity> existingUser = userService.findUserByUsername(request.getUsername());
        if(existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Podana nazwa użytkownika jest już zarejestrowana, wybierz inną lub zaloguj się!");
        }
        UserEntity existingUserEmail = userService.findUserByEmail(request.getEmail());
        if(existingUserEmail != null) {
            return ResponseEntity.badRequest().body("Podany adres email jest już zarejestrowany! Zaloguj się!");
        }
        userService.registerUser(request);
        return ResponseEntity.ok("Zarejestrowano poprawnie!");
    }

}