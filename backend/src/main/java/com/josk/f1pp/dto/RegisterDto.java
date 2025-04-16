package com.josk.f1pp.dto;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class RegisterDto {
    public String username;
    @Email
    public String email;
    public String password;
}