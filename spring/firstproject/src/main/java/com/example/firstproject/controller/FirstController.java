package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi") // url 요청 접수
    public String niceToMeetYou(){
        return "greetings"; // greeting.mustache 파일 변환
    }

}
