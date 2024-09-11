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

        // 로그인 설정
        http.formLogin(login -> login
                                    .loginPage("/user/login")
                                    .defaultSuccessUrl("/")
                                    .failureUrl("/user/login?succuess=100")
                                    .usernameParameter("username")
                                    .passwordParameter("password"));


        // 로그아웃 설정
        http.logout(logout -> logout
                                .invalidateHttpSession(true)
                                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                                .logoutSuccessUrl("/user/login?success=101")); // 이동할 리다이렉트 주소



        // 인가 설정 (인증 -> 인가) (권한 검사)
        http.authorizeHttpRequests(authorrize -> authorrize
                                            .requestMatchers("/").permitAll()
                                            .requestMatchers("/admin/**").hasAuthority("ADMIN")
                                            .requestMatchers("/manager/**").hasAnyAuthority("ADMIN", "MANAGER")
                                            .requestMatchers("/staff/**").hasAnyAuthority("ADMIN", "MANAGER", "STAFF")
                                            .anyRequest().permitAll());

        
        // 기타 보안 설정 (csrf 공격)
        http.csrf(configure-> configure.disable());

        return http.build();
    }

    // 평문을 암호화 시킬거임
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
