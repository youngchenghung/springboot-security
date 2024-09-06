package com.example.springboot_security.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.springboot_security.dao.MemberDao;
import com.example.springboot_security.model.Member;

@Component
public class MyUserDetailsService  implements UserDetailsService {

        @Autowired
        private MemberDao memberDao;

        // 根據使用者名稱查詢使用者資料
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            // 從資料庫 member 表中查詢使用這資料
            Member member = memberDao.getMemberByEmail(username);
            
            // 如果使用者不存在，拋出例外
            if (member == null) {
                throw new UsernameNotFoundException("Member not found for : " + username);
            }

            // 如果使用者存在，則返回使用者資料
            else {
                String memberEmail = member.getEmail();
                String memberPassword = member.getPassword();
                
                // 權限列表
                List<GrantedAuthority> authorities = new ArrayList<>();

                // 返回使用者資料, Spring Security 指定的 User 格式
                return new User(memberEmail, memberPassword, authorities);
            }

        }

}
