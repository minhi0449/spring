package com.ch05.controller;

import com.ch05.dto.User1DTO;
import com.ch05.service.User1Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class User1Controller {

    private final User1Service user1Service;

    @Autowired
    public User1Controller(User1Service user1Service){
        this.user1Service = user1Service;
        log.info("User1Controller 생성자 호출됨 - 의존성 주입 완료");
    }

    @GetMapping("/user1/list")
    public String list(Model model){
        List<User1DTO> users = user1Service.selectUser1s();
        model.addAttribute("users", users);
        return "user1/list";
    }

    @GetMapping("/user1/register")
    public String register(){
        return "user1/list";
    }

    @PostMapping("/user1/register")
    public String register(User1DTO user1DTO){
        user1Service.insertUser1(user1DTO);
        return "redirect:/user1/list";
    }

    @GetMapping("/user1/modify")
    public String modify(@RequestParam("uid") String uid, Model model){
        User1DTO user1DTO = user1Service.selectUser1(uid);
        model.addAttribute(user1DTO);
        return "user1/modify";
    }

    @PostMapping("/user1/modify")
    public String modify(User1DTO user1DTO){
        user1Service.updateUser1(user1DTO);
        return "redirect:/user1/list";
    }

    @GetMapping("/user1/delete")
    public String delete(@RequestParam("uid") String uid){
        user1Service.deleteUser1(uid);
        return "redirect:/user1/list";
    }
}
