package com.helmes.sectors.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDto {
    @NotBlank
    @Size(min = 2, message = "Username must be at least 8 characters long")
    private String username;

    @NotBlank
    @Size(min = 2, message = "Password must be at least 8 characters long")
    private String password;
}