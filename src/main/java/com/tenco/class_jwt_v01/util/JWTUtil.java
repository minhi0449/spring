package com.tenco.class_jwt_v01.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component // Ioc 처리 (다른 곳에서 쉽게 DI 가능 싱글톤으로 동작)
public class JWTUtil {
    @Value("${jwt.secret}")
    private String secret; // jwt1234

    // 사용자 로그인 --> username, password (이 맞다면 ?)--> JWT 발급 (왕국 시크릿 키 발급)
    // 엑세스 토큰 생성(10분)
    public String generateAccessToken(String username){
        // 로그인은 service 에서 처리할 수 있으므로,
        // 여기서 집중할 것은 엑세스 토큰 발급을 집중할 것
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withSubject("myApp")
                    .withClaim("username", username)
                    .withClaim("role", "USER")
                    .withIssuedAt(new Date())// 페이로드에 발행 시간 설정
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 10)) // 만료 시간을 10분 제한
                    .sign(algorithm);
        }catch (JWTCreationException e){
            throw new RuntimeException("액세스 토큰 생성 실패", e)
        }
    }

}
