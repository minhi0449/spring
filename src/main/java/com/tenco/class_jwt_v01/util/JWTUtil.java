package com.tenco.class_jwt_v01.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


/*
    2025.02.25 (화) -
 */

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
                    .withIssuedAt(new Date()) // 페이로드에 발행 시간 설정
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 30)) // 만료 시간을 10분 제한
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("액세스 토큰 생성 실패", e);
        }
    }

    // 리플레쉬 토큰 생성(7일)
    public String generateRefreshToken(String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret); // 동일한 서명 알고리즘
            return JWT.create()
                    .withSubject("myApp")
                    .withClaim("username", username)
                    .withClaim("role", "USER")
                    .withIssuedAt(new Date()) // 페이로드에 발행 시간 설정
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // 만료 시간을 7일 제한
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("리프레시 토큰 생성 실패", e);
        }
    }


    // JWT 토큰 username 압축 풀어서 꺼내는 기능
    public String extractUsername(String token){
        return JWT.decode(token) // 넘겨받은 token을 통으로 받고
                .getClaim("username")
                .asString();
    }

    // JWT 토큰 role 압축 풀어서 꺼내는 기능
    public String extractRole(String token){
        return JWT.decode(token) // 넘겨받은 token을 통으로 받고
                .getClaim("role")
                .asString();
    }

    // 토큰 유효성 검사
    // 토큰 시간이 유효한지, 위 변조 되지 않았는지 확인
    public boolean validateToken(String token, String username) {
        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWT.require(algorithm) // 이 알고리즘으로 서명된 토큰만 허용
                    .build()
                    .verify(token); // 토큰 검증 (만료시간, 위조 체크)
            String extractedUsername = extractUsername(token);
            return extractedUsername != null && extractedUsername.equals(username);
            // && 빠른 평가 short 에볼루션

        } catch (Exception e) {
            return false;
        }
    }


}
