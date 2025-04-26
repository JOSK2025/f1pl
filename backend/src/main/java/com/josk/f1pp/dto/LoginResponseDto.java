package com.josk.f1pp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private String login;
    private String email;
    private List<String> roles;
    private String jwtToken;
}