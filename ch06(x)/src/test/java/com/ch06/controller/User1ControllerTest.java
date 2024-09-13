package com.ch06.controller;

import com.ch06.dto.User1DTO;
import com.ch06.service.User1Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultHandlersDsl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.text.View;


@SpringBootTest
@AutoConfigureMockMvc
public class User1ControllerTest {



    @Autowired
    private MockMvc mockMvc;

    @Test
    void list(){

        mockMvc.perform(get("/user1/list"));
            .andExpect(status().isOk())
                .andExpect(View().name("/user1/list"))
                .andDo(print());


    }

    @GetMapping("/user1/register")
    public String register(){
        return "/user1/register";
    }

    @PostMapping ("/user1/register")
    public String register(User1DTO user1DTO){
        user1Service.insertUser1(user1DTO);

        return "redirect:/user1/list";
    }

    @Test
    void register(){
    for(int i=1; i<=10000; i++){
        mockMvc.perform(
                    post("/user1/register")
                            .param("uid", "a101")
                            .param("name", "테스트")
                            .param("birth", "1990-01-01")
                            .param("hp", "010-1234-1001")
                            .param("age", 22)
                .andExpect(status().is3xxRerirection())
                .andExpect(redirectedUrl("/user1/list"))
                .andDo(print());




        }





        @Test
        void delete() throw Exception{
            for(int i=1; i<=10000; i++){
                mockMvc.perform(get("/user1/delete")
                        .p
                )
            }


    }




    }

}
