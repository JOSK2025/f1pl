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

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/api/auth/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto request) {
        if(request.username == null || request.username.isEmpty()) {
            return ResponseEntity.ok("failed");
        }
        if(request.email == null || request.email.isEmpty()) {
            return ResponseEntity.ok("failed");
        }
        if(request.password == null || request.password.isEmpty()) {
            return ResponseEntity.ok("failed");
        }
        Optional<UserEntity> existingUser = userService.findUserByUsername(request.getUsername());
        if(existingUser.isPresent()) {
            return ResponseEntity.ok("failed");
        }
        UserEntity existingUserEmail = userService.findUserByEmail(request.getEmail());
        if(existingUserEmail != null) {
            return ResponseEntity.ok("failed");
        }
        userService.registerUser(request);
        return ResponseEntity.ok("User registered successfully");
    }

}