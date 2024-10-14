package com.example.diploma_generator_spring.dto;

import com.example.diploma_generator_spring.model.UserRole;

public record RegisterDTO(
        String login,
        String senha,
        UserRole role
) {
}
