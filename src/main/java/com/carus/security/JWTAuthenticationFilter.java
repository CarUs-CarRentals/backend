package com.carus.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.carus.config.AuthenticationConfig;
import com.carus.entities.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationConfig config;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationConfig config) {
        this.authenticationManager = authenticationManager;
        this.config = config;

        setFilterProcessesUrl("/api/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserEntity user = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        UserEntity user = (UserEntity) authResult.getPrincipal();

        long currentMili = System.currentTimeMillis();

        String token = JWT.create()
                .withSubject(user.getLogin())
                .withExpiresAt(new Date(currentMili + config.getTokenExpiration()))
                .sign(Algorithm.HMAC512(config.getTokenPassword()));

        String refreshToken = JWT.create()
                .withSubject(user.getLogin())
                .withExpiresAt(new Date(currentMili + 900_000_000 * config.getTokenExpiration()))
                .sign(Algorithm.HMAC512(config.getTokenPassword()));

        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("tokenExpiration", currentMili + config.getTokenExpiration());
        data.put("refreshToken", refreshToken);
        data.put("refreshTokenExpiration", currentMili + 2 * config.getTokenExpiration());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), data);
    }
}
