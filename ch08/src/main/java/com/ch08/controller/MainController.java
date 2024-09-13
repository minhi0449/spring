package com.ch08.controller;

import com.ch08.security.MyUserDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class MainController {

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {


        // 로그인 사용자 인증객체 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication); // log 찍는 거 습관 들이기
        /*
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        User user = null;

        if(myUserDetails != null){
            user = myUserDetails.getUser();
        }
        */
        // model.addAttribute("user", user);

        return "index";
    }

    @GetMapping(value = {"/admin", "/admin/index"})
    public String adminIndex(){
        return "/admin/index/";
    }

    @GetMapping(value = {"/manager", "/manager/index"})
    public String managerIndex(){
        return "/manager/index/";
    }

    @GetMapping(value = {"/staff", "/staff/index"})
    public String staffIndex(){
        return "/staff/index/";
    }

}
