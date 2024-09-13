package com.ch08.security;

import com.ch08.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@ToString
@Builder
public class MyUserDetails implements UserDetails {


    // User 엔티티 선언
    private User user;
    // MyUserDetails
    // UerDetailService

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // 계정이 갖는 권한 목록 생성
        List<GrantedAuthority> authorities = new ArrayList<>();
        // authorities.add(new SimpleGrantedAuthority("ROLE_USER")); // 접두어 ROLE 이 붙음
        // 접두어 가지고 권한 검사

        authorities.add(new SimpleGrantedAuthority("ROLE_" +user.getRole())); // 계정 권한 앞에 접두어 Role_붙여야 함
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPass();
    }

    @Override
    public String getUsername() {
        return user.getUid();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정 만료 여부 (true : 만료 안 됨, false: 만료)
        // (휴먼 계정 만들어줘야 하는데 지금 사용 안하는 거니까 true)
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠금 (악동이라서 계정 잠금 약간 블랙리스트)
        // 우리는 사용 안 해서 true
        // 계정 잠김 여부 (true: 잠김 아님 , false: 잠김)
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // 비밀번호 유효기간 둬서 관리함 (3개월 뒤에 비번 변경)
        // 비밀번호 만료 여부 (true : 만료 안 됨, false: 만료)
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 계정 활성화 여부 (true : 활성화, false : 비활성)
        return true;
    }
}
