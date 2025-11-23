package com.example.authservice.entity;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ADMIN"),
    PROFESSOR("PROFESSOR"),
    STUDENT("STUDENT");

    private final String role;

    Role(String role) {
        this.role = role;
    }

}