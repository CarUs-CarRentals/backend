package com.carus.controllers;

import com.carus.dto.RegisterUserDTO;
import com.carus.dto.UpdateUserDTO;
import com.carus.dto.UserDTO;
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

    @GetMapping("/logged")
    public ResponseEntity<?> loggedUser() {
        return ResponseEntity.ok(userService.getLoggedUserDTO());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserDTO> update(@RequestBody UpdateUserDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(userService.update(dto, id));
    }
}
