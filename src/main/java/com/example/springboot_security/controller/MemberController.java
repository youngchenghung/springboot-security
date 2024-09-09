package com.example.springboot_security.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.example.springboot_security.dao.MemberDao;
import com.example.springboot_security.model.Member;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MemberController {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 註冊使用者帳號
    @PostMapping("/register")
    public Member register(@RequestBody Member member) {

        // 將密碼進行加密
        String hashedPassword = passwordEncoder.encode(member.getPassword());
        // 將加密後的密碼設定回使用者物件
        member.setPassword(hashedPassword);

        // 在資料庫中新增使用者資料
        Integer memberId = memberDao.createMember(member);

        return memberDao.getMemberById(memberId);
    }

    @GetMapping("/welcom")
    public String welcome() {
        return "welcom";
    }
    
    // 取得使用者資料
    @GetMapping("/hello")
    public String hello(Authentication authentication) {

        // 取得使用者名稱
        String username = authentication.getName();

        // 取得使用者權限
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        return "hello " + username + " " + authorities;
    }

}
