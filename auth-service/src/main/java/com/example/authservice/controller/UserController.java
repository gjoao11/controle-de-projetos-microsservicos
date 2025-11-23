package com.example.authservice.controller;

import com.example.authservice.dto.CreateUserDTO;
import com.example.authservice.entity.User;
import com.example.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userSerive;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateUserDTO body) {
        User newUser = userSerive.create(body);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userSerive.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        Optional<User> user = Optional.ofNullable(userSerive.findById(id));
        return user.map(value ->
                        ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
