package com.josk.f1pp.service;

import com.josk.f1pp.dto.RegisterDto;
import com.josk.f1pp.model.Role;
import com.josk.f1pp.model.UserEntity;
import com.josk.f1pp.repository.RoleRepository;
import com.josk.f1pp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserEntity> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void registerUser(RegisterDto request) {
        UserEntity user = new UserEntity();
        user.setUsername(request.username);
        user.setEmail(request.email);
        user.setPassword(passwordEncoder.encode(request.password));
        Role role = roleRepository.findByName("USER");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

}
