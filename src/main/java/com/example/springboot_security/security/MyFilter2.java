package com.example.springboot_security.security;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyFilter2 extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("執行 MyFilter2");

        // 判斷條件 路徑是否為 /hello
        String url = request.getRequestURI();
        if (url.equals("/hello")) {
            System.out.println("path 為 /hello，通過 MyFilter2");

            // 將request, response 繼續執行給下一個過濾器鏈
            filterChain.doFilter(request, response);
        }

        else {
            System.out.println("path 不為 /hello，不通過 MyFilter2");
            response.setStatus(500);
        }

    }
}
