package com.carus.controllers;

import com.carus.config.AuthenticationConfig;
import com.carus.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class TokenController {

    @Autowired
    private AuthenticationConfig authenticationConfig;

    @Autowired
    private UserService service;

    @PostMapping(value = "/refresh-token")
    public ResponseEntity<Map<String, Object>> refreshToken(HttpServletRequest request) {
        return ResponseEntity.ok(service.refreshToken(request));
    }

    @GetMapping("/logged")
    public ResponseEntity<?> loggedUser() {
        return ResponseEntity.ok(service.getLoggedUserDTO());
    }
}
