package com.carus.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.carus.dto.RegisterUserDTO;
import com.carus.dto.UserDTO;
import com.carus.entities.UserEntity;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

//    @Value("${authentication.password}")
    public String TOKEN_PASSWORD = "fca54529-840a-4ac4-b1de-03cd4a14b687";

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

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.save(dto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.save(dto));
    }

    @PostMapping(value = "/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String header = "Authorization";
        String attPrefix = "Bearer ";
        String attribute = request.getHeader(header);

        if (attribute == null || !attribute.startsWith(attPrefix)) {
            return ResponseEntity.badRequest().build();
        }

        String refreshToken = attribute.replace(attPrefix, "");
        String username = JWT.require(Algorithm.HMAC512(TOKEN_PASSWORD))
                .build()
                .verify(refreshToken)
                .getSubject();

        UserEntity user = (UserEntity) userService.loadUserByUsername(username);
        long currentMili = System.currentTimeMillis();
        String newToken = JWT.create()
                .withSubject(user.getLogin())
                .withExpiresAt(new Date(currentMili + 10_000))
                .sign(Algorithm.HMAC512(TOKEN_PASSWORD));

        return ResponseEntity.ok(newToken);
    }
}
