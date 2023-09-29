package com.project.management.devboard.controllers.dto;

import com.project.management.devboard.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {
    private String token;
    private User user;
}
