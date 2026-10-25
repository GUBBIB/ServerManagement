package com.github.gubbib.backend.config;

import com.github.gubbib.backend.security.jwt.JwtProperties;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Configuration
public class JwtConfig {

    @Bean
    public SecretKey jwtSecretKey(JwtProperties jwtProperties) {
        return Keys.hmacShaKeyFor(
                jwtProperties.secret().getBytes(StandardCharsets.UTF_8)
        );
    }
}
