package com.josk.f1pp.controller;

import com.josk.f1pp.config.JWTUtility;
import com.josk.f1pp.dto.LoginDto;
import com.josk.f1pp.dto.LoginResponseDto;
import com.josk.f1pp.dto.RegisterDto;
import com.josk.f1pp.model.Role;
import com.josk.f1pp.model.UserEntity;
import com.josk.f1pp.security.JoskUserDetailsService;
import com.josk.f1pp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JoskUserDetailsService userDetailsService;
    private final JWTUtility jwtUtility;

    @Autowired
    public AuthController(UserService userService,
                          AuthenticationManager authenticationManager,
                          JoskUserDetailsService userDetailsService,
                          JWTUtility jwtUtility) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtility = jwtUtility;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginDto loginDto) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getEmail());
        UserEntity userEntity = userService.findByEmail(loginDto.getEmail());
        final String token = jwtUtility.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponseDto(
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getRoles().stream().map(Role::getName).collect(Collectors.toList()),
                token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto request) {
        if (request.username == null || request.username.isEmpty()) {
            return ResponseEntity.badRequest().body("Podaj nazwę użytkownika!");
        }
        if (request.email == null || request.email.isEmpty()) {
            return ResponseEntity.badRequest().body("Podaj adres email!");
        }
        if (request.password == null || request.password.isEmpty()) {
            return ResponseEntity.badRequest().body("Podaj swoje hasło!");
        }
        Optional<UserEntity> existingUser = userService.findUserByUsername(request.getUsername());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Podana nazwa użytkownika jest już zarejestrowana, wybierz inną lub zaloguj się!");
        }
        Optional<UserEntity> existingUserEmail = userService.findUserByEmail(request.getEmail());
        if (existingUserEmail.isPresent()) {
            return ResponseEntity.badRequest().body("Podany adres email jest już zarejestrowany! Zaloguj się!");
        }
        userService.registerUser(request);
        return ResponseEntity.ok("Zarejestrowano poprawnie!");
    }
}
