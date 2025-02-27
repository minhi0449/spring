package com.tenco.class_jwt_v01.controller;

import com.tenco.class_jwt_v01.domain.User;
import com.tenco.class_jwt_v01.dto.LoginResponseDto;
import com.tenco.class_jwt_v01.dto.RegisterRequestDto;
import com.tenco.class_jwt_v01.service.UserService;
import com.tenco.class_jwt_v01.util.JWTUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    JWTUtil jwtUtil;


    // 회원 가입 페이지 , 회원 가입 기능 처리
    @GetMapping("/register")
    public String showRegisterForm() {
        log.info("📍여기는 컨트롤러 showRegisterForm() / GetMapping()");
        return "register";
    }

    @PostMapping("/register")
    public String register(RegisterRequestDto dto) {
        log.info("📍여기는 컨트롤러 register() / PostMapping()");

        // 유효성 검사 생략...
        userService.register(dto);
        return "redirect:/login";
    }

    // 로그인 페이지 , 로그인 기능 처리
    @GetMapping("/login")
    public String showLoginForm() {
        log.info("📍여기는 컨트롤러 showLoginForm() / GetMapping()");
        return "login";
    }

    // key = 값
    // 로그인 성공 시 -> welcome.mustache 파일로 이동
    @PostMapping("/login")
    public String login(String username, String password, Model model){
        log.info("📍여기는 컨트롤러 login() / PostMapping()");

        LoginResponseDto response = userService.login(username, password);
        log.info("📍컨트롤러(로그인) response : " + response);
        if (response != null){
            model.addAttribute("accessToken", response.getAccessToken());
            log.info("📍컨트롤러(로그인) response.getAccessToken() : " + response.getAccessToken());

            model.addAttribute("refreshToken", response.getRefreshToken());
            log.info("📍컨트롤러(로그인) response.getRefreshToken() : " + response.getRefreshToken());

            model.addAttribute("username", response.getUsername());
            log.info("📍컨트롤러(로그인) response.getUsername() : " + response.getUsername());

            return "welcome";
        }
        model.addAttribute("error", "아이디 또는 비밀번호가 잘못됨");

        return "login";

    }


    // SSR 에서 수동 갱신
    // SSR 에서는 클라이언트 측 (로직 : ajax) 없으면 자동화 어려움
    // SSR로 자동 갱신 로직을 만들어 본다면
    // 클라이언트가 401 UNAUTHORIZED 받으면 AJAX 로 받으면 refresh 로 요청하면 된다.
    @PostMapping("/refresh")
    public String refreshToken(@RequestParam String refreshToken, Model model){
        log.info("📍여기는 컨트롤러 refreshToken()");
        // 리프레시 토큰 생성 --> welcome
        // 실패시 --> login( 사용자가 위변조 할 수 있으니까)
        // 사용자가 던지는 refreshToken 과 DB 에 저장되어 있는 refreshToken 대조
        // 및 토큰 유효성 검사
        String newAccessToken = userService.refreshAccessToken(refreshToken);
        if(newAccessToken != null){
            String username = jwtUtil.extractUsername(newAccessToken);
            model.addAttribute("accessToken", newAccessToken);
            model.addAttribute("refreshToken", refreshToken); // 기존 리프레시 토큰 7일간 사용하는 거 사용
            model.addAttribute("username", username);
            return "welcome"; // welcome 파일로 이동
            // 7일 지나면 통과 하지 못하고 error 로 내려감
        }
        model.addAttribute("error", "리프레시 토큰이 만료되었거나, 유효하지 않습니다.");
        return "login";
    }

    // JWT 인증 페이지 테스트
    @GetMapping("/protected")
    public String protectedPage(@RequestParam String accessToken, Model model){
        log.info("📍여기는 컨트롤러 protectedPage()");
        String username = jwtUtil.extractUsername(accessToken);
        // 토큰 유효성 검사
        if(jwtUtil.validateToken(accessToken, username)){
            model.addAttribute("username", username);
            return "protected";
        }
        return "redirect:/login";
    }

    // 로그아웃
    @PostMapping("/logout")
    public String logout(String refreshToken){
        log.info("📍여기는 컨트롤러 logout()");
        userService.logout(refreshToken);
        return "redirect:/login";
    }



}
