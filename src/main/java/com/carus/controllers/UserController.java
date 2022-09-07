package com.carus.controllers;

import com.carus.dto.UserDTO;
import com.carus.entities.UserEntity;
import com.carus.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping(path = "/create")
    public ResponseEntity<UserDTO> create(@RequestBody UserEntity newUser) {
        return ResponseEntity.ok(userService.create(newUser));
    }
}
