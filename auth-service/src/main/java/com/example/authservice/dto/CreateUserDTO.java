package com.example.authservice.dto;

import com.example.authservice.entity.Role;

public record CreateUserDTO(String name, String email, String password, Role role) {
}
