package com.hmsapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http.authorizeHttpRequests().anyRequest().permitAll();
        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);
//        http.csrf().disable().cors().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/api/auth/login", "/api/auth/sign-up","/api/auth/property/sign-up")
//                .permitAll()
//                .requestMatchers("/api/v1/property/addproperty")
//                .hasRole("OWNER")
//                .requestMatchers("/api/v1/property/addproperty","/api/v1/property/deleteproperty")
//                .hasAnyRole("ADMIN","OWNER")
//                .requestMatchers("/api/auth/blog/sign-up")
//                .hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .sessionManagement().disable();
        return http.build();
    }
}
