package com.ch08.controller;

import com.ch08.dto.UserDTO;
import com.ch08.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @GetMapping("/user/login")
    public String login(){
        return "/user/login";
    }

    @GetMapping("/user/register")
    public String register(){
        return "/user/register";
    }



    @PostMapping("/user/register")
    public String register(UserDTO userDTO){
        UserService.insertUser(userDTO);
        return "redi/user/register";
    }



}
