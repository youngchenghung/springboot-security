package com.example.springboot_security.dao.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.example.springboot_security.dao.MemberDao;
import com.example.springboot_security.model.Member;
import com.example.springboot_security.rowmapper.MemberRowMapper;

@Component
public class MemberDaoImpl implements MemberDao{

    @Autowired 
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private MemberRowMapper memberRowMapper;

    // 透過 memberId 取得資料庫會員資料
    @Override
    public Member getMemberById(Integer memberId) {

        String sql = "SELECT * FROM member WHERE member_id = :memberId";

        Map<String, Object> map = new HashMap<>();
        map.put("memberId", memberId);

        List<Member> memberList = namedParameterJdbcTemplate.query(sql, map, memberRowMapper);

        if (memberList.size() > 0) {
            return memberList.get(0);
        }
        else {
            return null;
        }

    }

    // 透過 email 取得資料庫會員資料
    @Override
    public Member getMemberByEmail(String email) {
        String sql = "SELECT member_id, email, password, name, age FROM member WHERE email = :email";

        Map<String, Object> map = new HashMap<>();
        map.put("email", email);

        List<Member> memberList = namedParameterJdbcTemplate.query(sql, map, memberRowMapper);

        if (memberList.size() > 0) {
            return memberList.get(0);
        } else {
            return null;
        }
    }


    // 建立會員資料
    @Override
    public Integer createMember(Member member) {
        String sql = "INSERT INTO member (email, password, name, age) VALUES (:email, :password, :name, :age)";

        Map<String, Object> map = new HashMap<>();
        map.put("email", member.getEmail());
        map.put("password", member.getPassword());
        map.put("name", member.getName());
        map.put("age", member.getAge());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int id = keyHolder.getKey().intValue();
        System.out.println("id: " + id);

        return id;

    }
    
}
