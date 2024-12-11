package com.hmsapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecutityConfig {
    @Autowired
    JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http.csrf().disable().cors().disable();
        //http.authorizeHttpRequests().anyRequest().permitAll();
       http.addFilterBefore(jwtFilter, AuthorizationFilter.class);
        http.authorizeHttpRequests()
                .requestMatchers("/api/auth/login","/api/auth/sign-up").permitAll()
                .requestMatchers("/api/v1/property/addproperty","/api/v1/property/deleteproperty")
                .hasAnyRole("PROPERTYOWNER","ADMIN")
                .anyRequest().authenticated();
        return http.build();

    }


}
