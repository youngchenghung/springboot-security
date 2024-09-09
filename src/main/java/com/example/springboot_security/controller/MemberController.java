package com.example.springboot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    // 註冊使用者帳號
    @PostMapping("/register")
    public Member register(@RequestBody Member member) {

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
