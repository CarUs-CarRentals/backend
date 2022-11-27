package com.carus.controllers;

import com.carus.dto.RegisterUserDTO;
import com.carus.dto.UpdateUserDTO;
import com.carus.dto.UserDTO;
import com.carus.dto.UserProfileDTO;
import com.carus.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<UserDTO> create(@RequestBody RegisterUserDTO newUser) {
        return ResponseEntity.ok(userService.create(newUser));
    }

    @GetMapping(path = "/{uuid}")
    public ResponseEntity<UserDTO> findById(@PathVariable String uuid) {
        return ResponseEntity.ok(userService.findByUuid(uuid));
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity<Void> deleteById(@PathVariable String uuid) {
        userService.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{uuid}")
    public ResponseEntity<UserDTO> update(@RequestBody UpdateUserDTO dto, @PathVariable String uuid) {
        return ResponseEntity.ok(userService.update(dto, uuid));
    }

    @GetMapping(path = "/profile")
    public ResponseEntity<UserProfileDTO> getUserProfile() {
        return ResponseEntity.ok(userService.getUserProfile());
    }
}
