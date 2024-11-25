package com.univerity.hallmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/public/**").permitAll() // Public URLs
                        .requestMatchers("/api/users/**").permitAll()
                        .anyRequest().authenticated()               // Secure all other URLs
                )
                .formLogin(withDefaults()); // Enable form-based login with default login page

        return http.build();
    }
}
