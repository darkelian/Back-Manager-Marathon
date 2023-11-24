package com.demo.demo.Dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "username vacío")
    private String username;
    @NotBlank(message = "contraseña vacia")
    private String password;
}
