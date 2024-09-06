package com.example.springboot_security.dao;

import com.example.springboot_security.model.Member;

public interface MemberDao {

    Member getMemberById(Integer memberId);

    Member getMemberByEmail(String email);

    Integer createMember(Member member);
}
