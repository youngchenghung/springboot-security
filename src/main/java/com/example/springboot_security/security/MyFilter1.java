package com.example.springboot_security.security;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class MyFilter1 implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
 
        System.out.println("執行 MyFilter1");

        // 將request, response 繼續執行給下一個過濾器鏈
        filterChain.doFilter(servletRequest, servletResponse);
    
    }

}
