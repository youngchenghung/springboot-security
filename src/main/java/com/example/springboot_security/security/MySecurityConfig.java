package com.example.springboot_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.Customizer;


@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    // 設定密碼加密方式
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 密碼布進行加密方法(NoOpPasswordEncoder)
        //return NoOpPasswordEncoder.getInstance();

        // 使用 BCryptPasswordEncoder 進行密碼加密
        return new BCryptPasswordEncoder();
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

    // 設定安全過濾器鏈
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        // 設定不使用 CSRF，啟用 HTTP Basic 認證，啟用表單登入
        return http
            .csrf(csrf -> csrf.disable())
            .httpBasic(Customizer.withDefaults())
            .formLogin(Customizer.withDefaults())

            .authorizeRequests(request -> request
                .requestMatchers("/register", "/welcom").permitAll()
                .requestMatchers("/hello").hasRole("ADMIN")
                .anyRequest().denyAll()
            )
            .build();
    }
}
