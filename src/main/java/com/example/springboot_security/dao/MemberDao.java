package com.example.springboot_security.dao;

import java.util.List;

import com.example.springboot_security.model.Member;
import com.example.springboot_security.model.Role;

public interface MemberDao {

    Member getMemberById(Integer memberId);

    Member getMemberByEmail(String email);

    Integer createMember(Member member);

    List<Role> getRolesByMemberId(Integer memberId);
}
