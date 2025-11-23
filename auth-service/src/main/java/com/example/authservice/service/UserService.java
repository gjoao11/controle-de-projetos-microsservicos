package com.example.authservice.service;

import com.example.authservice.dto.CreateUserDTO;
import com.example.authservice.entity.User;
import com.example.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User create(CreateUserDTO dto) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        return this.userRepository.save(new User(dto.name(), dto.email(), encryptedPassword, dto.role()));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}