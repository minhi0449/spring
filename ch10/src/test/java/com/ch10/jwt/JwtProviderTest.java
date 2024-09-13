package com.ch10.jwt;

import com.ch10.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtProviderTest {

    @Autowired
    private JwtProvider jwtProvider;


    @Test
    void createToken() {

        User user = User.builder()
                .uid("a101")
                .name("김유신")
                .birth("1990-09-09")
                .role("ADMIN")
                .build();

        String jwt = jwtProvider.createToken(user, 1);
        System.out.println(jwt);
    }

    @Test
    void getClaims() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjaGhhazA1MDNAZ21haWwuY29tIiwiaWF0IjoxNzI2MTI3MDAxLCJleHAiOjE3MjYyMTM0MDEsInVzZXJuYW1lIjoiYTEwMSIsInJvbGUiOiJBRE1JTiJ9.C0zkty9ymYgN24SDXIMdU2QWZQYfe6r-2Eh1uo69vYU";

        Claims claims = jwtProvider.getClaims(token);
        String username = claims.get("username", String.class);
        String role = claims.get("role", String.class);

        System.out.println(username + ", " + role);
    }

    @Test
    void getAuthentication() {
    }

    @Test
    void validateToken() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjaGhhazA1MDNAZ21haWwuY29tIiwiaWF0IjoxNzI2MTI3MDAxLCJleHAiOjE3MjYxMjcwMDEsInVzZXJuYW1lIjoiYTEwMSIsInJvbGUiOiJBRE1JTiJ9.g55qkmiCRANQaIUZB53XE4RlfjsxvNZhBGzKoZOv5_4";

        try {
            jwtProvider.validateToken(token);
            System.out.println("토큰 이상 없음");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}