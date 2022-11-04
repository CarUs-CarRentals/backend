package com.carus.controllers;

import com.carus.config.AuthenticationConfig;
import com.carus.services.UserService;
import com.carus.services.exceptions.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class TokenController {

    @Autowired
    private AuthenticationConfig authenticationConfig;

    @Autowired
    private UserService service;

    @PostMapping(value = "/refresh-token")
    public ResponseEntity<String> refreshToken(HttpServletRequest request) {
        String newTokenOpt = service.refreshToken(request).orElseThrow(() -> new InternalServerErrorException("Token generation failure"));

        return ResponseEntity.ok(newTokenOpt);
    }
}
