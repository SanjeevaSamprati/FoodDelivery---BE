package com.fooddelivery.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityBypassConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // turn off CSRF check
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // allow all requests
            );
        return http.build();
    }
}
