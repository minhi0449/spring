package com.tenco.class_jwt_v01.api;

import com.tenco.class_jwt_v01.dto.LoginResponseDto;
import com.tenco.class_jwt_v01.dto.RegisterRequestDto;
import com.tenco.class_jwt_v01.dto.ResponseAPI;
import com.tenco.class_jwt_v01.service.UserService;
import com.tenco.class_jwt_v01.util.JWTUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/*
    2025.02.26 (수) - API 용 컨트롤러
 */

// API용 컨트롤러 : JSON 응답을 표준화된 ResponseAPI로 변환
@RestController
@RequestMapping("/api")
@Log4j2
public class ApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    // 회원가입 API : 사용자 등록 후 성공 응답 반환
    // post : register
    // 반환타입 : ResponseEntity<ResponseAPI<>>
    @PostMapping("/register")
    public ResponseEntity<ResponseAPI<String>> postRegister (
            @RequestBody RegisterRequestDto dto){
        log.info("🩵 여기는 API 컨트롤러 - postRegister()");
        // 1. 회원가입
        String register = userService.postRegister(dto);
        log.info("postRegister() - dto : "+ dto);
        // 2. 성공 응답 받기
        return ResponseEntity.ok(ResponseAPI.success("🩷회원가입 성공했슴도 -!", register));
    }


    // 로그인 API : 사용자 인증 후 토큰 반환
    // post("/login")
    @PostMapping("/login")
    public ResponseEntity<ResponseAPI<LoginResponseDto>> login(
            @RequestBody Map<String, String> request){
        String username = request.get("username");
        String password = request.get("password");

        LoginResponseDto responseDto = userService.login(username, password);

        if(responseDto != null){
            return ResponseEntity.ok(ResponseAPI.success("🧤로그인 성공", responseDto));
        }
        return ResponseEntity.status(401).body(ResponseAPI.error(
                "❌ 아이디 또는 비밀번호가 잘못되었습니다." , 401));
    }

    // 로그아웃 API : refreshToken 무료화
    // post("/logout")
    @PostMapping("/logout")
    public ResponseEntity<ResponseAPI<String>> logout(
            @RequestBody Map<String, String> requestDto){
        log.info("🩵 여기는 API 컨트롤러 - logout()");

        String refreshToken = requestDto.get("refreshToken");
        userService.logout(refreshToken);
        return ResponseEntity.ok(ResponseAPI.success("빠빠이 -🧤🧤", null));
    }


    // 토큰 갱신 API : 새 accessToken 발급
    // post("/refresh")

    // 보호된 API : 토큰 검증 후 접근 허용
    // !! [[헤더에서 Authorization 키와 값을 추출해서 응답]]
    // get("/protected")


}
