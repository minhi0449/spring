package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // ① 컨트롤러 선언
public class FirstController {

    @GetMapping("/hi") // ② url 요청 접수
    public String niceToMeetYou(Model model){ // ③ 메서드 수행, ④ model 객체 받아오기
        // model.addAttribute("변수명", 변숫값) // 변수값을 "변수명"이라는 이름으로 추가
        // model 객체기 "민희" 값을 "username"에 연결해 웹 브라우저로 보냄
        model.addAttribute("username", "민힁"); // ⑤ 모델 변수 등록
        // "민희" --> "민힁" 수정
        return "greetings"; // ⑥ 뷰 템플릿 페이지 반환 / greeting.mustache 파일 변환
    }

    @GetMapping("/bye") // URL 요청 접수
    public String seeYouNext(Model model){ // 메서드 작성 / model 객체 받아오기
        model.addAttribute("nickname", "홍길동"); // 모델 변수 등록하기
        return "goodbye"; // goodbye.mustache 반환
    }
}
