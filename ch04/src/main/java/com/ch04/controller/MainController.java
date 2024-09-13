package com.ch04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MainController {
// 사용자 정의 (의미를 부여한 Controller -> MainController)

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello(){
        System.out.println("hello...");
        // 요청 주소 이름으로 랜더링
        return "hello";
    }

    @GetMapping("/welcome")
    public String welcome(){
        System.out.println("welcome...");
        return "welcome";
    }

    @GetMapping("/greeting")
    public String greeting(){
        System.out.println("greeting...");
        return "greeting";
    }

}


























