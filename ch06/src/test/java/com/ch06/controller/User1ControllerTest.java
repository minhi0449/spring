package com.ch06.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class User1ControllerTest {

    @Autowired
    private MockMvc mockMvc; // mvc 테스트를 위한 가상 mvc 객체


    @Test
    void list() throws Exception {

        mockMvc.perform(get("/user1/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("/user1/list"))
                .andDo(print());
    }

    @Test
    void register() throws Exception {

        for(int i=1 ; i<1000 ; i++) {
            mockMvc.perform(
                            post("/user1/register")
                                    .param("uid", "abc"+i)
                                    .param("name", "테스트")
                                    .param("birth", "1990-01-01")
                                    .param("hp", "010-1234-1001")
                                    .param("age", "22"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/user1/list"))
                    .andDo(print());
        }
    }

    @Test
    void modify() {
    }

    @Test
    void delete() throws Exception {
        for(int i=1 ; i<10000 ; i++) {
            mockMvc.perform(get("/user1/delete")
                                    .param("uid", "abc"+i))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/user1/list"))
                    .andDo(print());
        }
    }
}