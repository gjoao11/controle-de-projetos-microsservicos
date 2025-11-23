package com.example.authservice.entity;

import com.example.authservice.dto.CreateUserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Role role;

    public User(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(CreateUserDTO dto) {
        this.name = dto.name();
        this.email = dto.email();
        this.password = dto.password();
        this.role = dto.role();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == Role.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        else if (role == Role.PROFESSOR) return List.of(new SimpleGrantedAuthority("ROLE_PROFESSOR"));
        return List.of(new SimpleGrantedAuthority("ROLE_STUDENT"));
    }

    @Override
    public String getUsername() {
        return email;
    }
}
