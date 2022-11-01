package com.carus.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("authentication")
public class AuthenticationConfig {

    private String tokenPassword;
}
