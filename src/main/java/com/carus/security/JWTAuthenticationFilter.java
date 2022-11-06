package com.carus.security;

import com.carus.config.AuthenticationConfig;
import com.carus.entities.UserEntity;
import com.carus.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationConfig config;

    private final UserService userService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationConfig config, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.config = config;
        this.userService = userService;

        setFilterProcessesUrl("/api/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserEntity user = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
            Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword());
            return authenticationManager.authenticate(auth);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException {
        UserEntity user = (UserEntity) authResult.getPrincipal();

        long expiration = System.currentTimeMillis() + config.getTokenExpiration();
        String token = userService.generateToken(user.getLogin());

        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("tokenExpiration", expiration / 1000);
        data.put("refreshToken", user.getRefreshToken());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), data);
    }
}
