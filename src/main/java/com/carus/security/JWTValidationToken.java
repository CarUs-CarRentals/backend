package com.carus.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.carus.config.AuthenticationConfig;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTValidationToken extends BasicAuthenticationFilter {

    private final AuthenticationConfig config;

    public JWTValidationToken(AuthenticationManager authenticationManager, AuthenticationConfig config) {
        super(authenticationManager);
        this.config = config;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String attribute = request.getHeader(config.getTokenRequestHeader());

        if (attribute == null || !attribute.startsWith(config.getTokenPrefix())) {
            chain.doFilter(request, response);
            return;
        }

        String token = attribute.replace(config.getTokenPrefix(), "");
        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        String username = JWT.require(Algorithm.HMAC512(this.config.getTokenPassword()))
                .build()
                .verify(token)
                .getSubject();

        return username == null ? null : new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
    }
}
