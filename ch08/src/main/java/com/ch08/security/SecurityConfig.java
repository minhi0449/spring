package com.ch08.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        /*
            스프링 시큐리티
              - Spring 프레임워크에서 제공하는 보안 관련 처리를 윈한 프레인ㅁ워크
              - Spring 기반 프로젝트는 Spring Security로 보안(인증, 인가) 처리 권장
              
            인증 설정
              - 로그인, 로그아웃 페이지 및 요청 주소 사용자 설정
              - 기본 일증(로그인, 로그아웃)은 Security가 제공하는 기본 인증페이지 동작
              - REST API 기반 인증에서는 토큰 방식을 이용하기 때문에 로그인, 로그아웃 비활성
                REST API 사용자 화면 x

            인가 설정
              - MyUserDatails의 사용자 권한 목록을 제공하는 getAuthorities()에서 반드시 접두어로 ROLE_ 붙여야 됨
              - ROLE_ 접두어를 안 붙이면 hasAuthority(), hasAnyAuthority()로nrjsgks tjfwjd
              - 존재하지 않은 요청주소에 대해서 시큐리티는 로그인 페이지로 redirect 수행하기 때문에 마지막에 anyRequest().permiAll() 권한설정
              (리다이렉트하고 또 리다이렉트 하기 때문에 오류남)
         */
        
        
        
        
        // 로그인 설정
        http.formLogin(login -> login
                .loginPage("/user/login")
                .defaultSuccessUrl("/user/success") // controller 요청 주소
                .failureUrl("/user/login?success=100")
                .usernameParameter("uid")
                .passwordParameter("pass"));

        // 로그아웃 설정
        http.logout(logout -> logout
                .invalidateHttpSession(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/user/login?success=101"));
        // 인가 설정
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER")
                .requestMatchers("/staff/**").hasAnyRole("ADMIN", "MANAGER", "STAFF")
                .anyRequest().permitAll()); // 인가 처리 후 반드시 붙여줘야 함
                // .anyRequest().permitAll()); 그 밖에 모든 것을 허용한다는 뜻
        
        

        // 기타 보안 설정
        http.csrf(configure -> configure.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}