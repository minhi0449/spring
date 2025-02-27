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
// 서버에서 API 문(door)을 만들 준비  (어노테이션 ↓)
@RestController // 나는 인터넷에서 요청을 받는 문이 될거야
@RequestMapping("/api") // 이 문은 /api 주소에서 열려 있어 !
@Log4j2
public class ApiController { // 이제부터 API 문을 만드는 공장을 시작
    // 이 공장은 "회원가입" , "로그인", "로그아웃" 같은 기능 담당
    // 서비스와 도구 연결하기
    @Autowired
    private UserService userService; // 회원 정보를 다루는 도구 (회원가입, 로그인 등)

    @Autowired
    private JWTUtil jwtUtil; // 토큰을 만들고 확인 하는 도구

    // 회원가입 API : 사용자 등록 후 성공 응답 반환
    // post : register
    // 반환타입 : ResponseEntity<ResponseAPI<>>
    // 새 친구를 등록하는 문 만들기
    @PostMapping("/register") // "register" 라는 문을 만들었음a
    public ResponseEntity<ResponseAPI<String>> postRegister (
            @RequestBody RegisterRequestDto dto){ // 친구 정보를 받음 (이름, 비밀번호 등)
        log.info("🩵 여기는 API 컨트롤러 - postRegister()"); // 로그 남기기 → "누군가 회원가입 요청을 했어요!" 라는 흔적을 남기는 거
        // 1. 회원가입
        // 새 친구를 등록하는 작업 진행
        // userService.postRegister(dto); → 받은 정보를 실제로 저장
        // (비유 : 학교에서 학생 명단에 새 친구 이름 적기)
        String register = userService.postRegister(dto);
        log.info("postRegister() - dto : "+ dto);
        // 2. 성공 응답 받기
        // "회원가입 성공!" 이라는 답장 보내기 → "이제부터 넌 우리 학교 학생이야!" 알려주는 거
        return ResponseEntity.ok(ResponseAPI.success("🩷회원가입 성공했슴도 -!", register));
    }


    // 로그인 API : 사용자 인증 후 토큰 반환
    // post("/login")
    @PostMapping("/login")
    public ResponseEntity<ResponseAPI<LoginResponseDto>> login(
            @RequestBody Map<String, String> request){ // 요청 본문을 Map (키-값 쌍)으로 받음
        // 사용자가 보낸 아이디와 비밀번호 정보를 받음
        // 요청에서 username 과 password 추출 - Map에서 키를 사용해 값을 가져옴
        // 입력 받은 정보 꺼내기 = 이름이랑 비밀번호를 확인해볼게
        String username = request.get("username"); // "username" 키의 값을 추출
        String password = request.get("password"); // "password" 키의 값을 추출

        // 로그인 시도
        // userService.login(username, password); // 이 사람 진짜 회원 맞아? 확인하는 과정
        // (비유 : "학교 들어가려면 학생증 보여줘!" 같은 느낌)
        LoginResponseDto responseDto = userService.login(username, password);

        // 로그인 성공 시 - responseDto 가 null 이 아니면
        if(responseDto != null){
            // 200 ok 와 함께 성공 응답과 로그인 정보(토큰 포함) 반환
            return ResponseEntity.ok(ResponseAPI.success("🧤로그인 성공", responseDto));
        }

        // 로그인 실패 시 - responseDto가 null 이면
        // 401 Unauthorized 상태코드와 함께 에러 응답 반환
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
    public ResponseEntity<ResponseAPI<Map<String, String>>> refreshToken(
            @RequestBody Map<String, String> requestDto){
        String refreshToken = requestDto.get("refreshToken");
        String newAccessToken = userService.refreshAccessToken(refreshToken);

        if (newAccessToken != null){
            Map<String, String> data = Map.of("accessToken", newAccessToken);
            return ResponseEntity.ok(ResponseAPI.success("토큰 갱신 성공", data));
        }

        return ResponseEntity.status(401)
                .body(ResponseAPI.error("리프레시 토큰이 만료되었거나, 유효하지 않습니다.", 401));
    }


    // 보호된 API : 토큰 검증 후 접근 허용
    // !! [[헤더에서 Authorization 키와 값을 추출해서 응답]]
    // get("/protected")
    @GetMapping("/protected")
    public ResponseEntity<ResponseAPI<Map<String, String>>> protectedApi(
            @RequestHeader("Authorization") String authHeader){
        String token = authHeader.replace("Bearer", "");
        String username = jwtUtil.extractUsername(token);
        if (jwtUtil.validateToken(token, username)) {
            Map<String, String> data = Map.of("username", username);
            return ResponseEntity.ok(ResponseAPI.success("보호된 API에 접근 성공", data));
        }
        return ResponseEntity.status(401)
                .body(ResponseAPI.error("액세스 토큰이 만료되었거나 유효하지 않습니다.", 401));
    }


}
