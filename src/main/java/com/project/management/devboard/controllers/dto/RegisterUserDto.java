package com.project.management.devboard.controllers.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegisterUserDto {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String confirmPassword;
    @NotEmpty
    private String name;
    @NotEmpty(message = "Please select a role for this user.")
    private String role;
}
