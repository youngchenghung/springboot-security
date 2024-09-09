package com.example.springboot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}
