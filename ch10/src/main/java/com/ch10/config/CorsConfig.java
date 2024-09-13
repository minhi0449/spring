package com.ch10.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 엔드포인트에 대한 접근허용
                .allowedOriginPatterns("http://127.0.0.1:5500")          // 모든 origin에서의 요청
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // OPTIONS: 사전체킹용
                .allowedHeaders("*")          // 모든 헤더 정보 허용
                .allowCredentials(true)       // 자격 증명 허용
                .maxAge(3600);                // pre-flight 요청의 유효시간 설정
    }

}