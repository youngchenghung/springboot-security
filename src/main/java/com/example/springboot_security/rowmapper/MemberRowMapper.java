package com.example.springboot_security.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.springboot_security.model.Member;

@Component
public class MemberRowMapper implements RowMapper<Member> {

    @Override
    public Member mapRow(ResultSet rs, int i) throws SQLException {

        Member member = new Member();
        member.setMemberId(rs.getInt("member_id"));
        member.setEmail(rs.getString("email"));
        member.setPassword(rs.getString("password"));
        member.setName(rs.getString("name"));
        member.setAge(rs.getInt("age"));

        return member;
    }
}
