package com.example.springboot_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    // @Bean
    // public InMemoryUserDetailsManager userDetailsManager() {

    //     // 創建兩個用戶，test1具有ADMIN和USER角色，test2具有USER角色

    //     UserDetails userTest1 = User
    //         .withUsername("test1")
    //         .password("{noop}1111")
    //         .roles("ADMIN", "USER")
    //         .build();

    //     UserDetails userTest2 = User
    //         .withUsername("test2")
    //         .password("{noop}2222")
    //         .roles("USER")
    //         .build();
            
    //     // 將用戶存儲在內存中
    //     return new InMemoryUserDetailsManager(userTest1, userTest2);
    // }
}
