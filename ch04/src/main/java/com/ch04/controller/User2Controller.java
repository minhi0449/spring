package com.ch04.controller;


import com.ch04.dto.User1DTO;
import com.ch04.service.User1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class User2Controller {

    private User1Service service;

    @Autowired
    public User2Controller(User1Service service) {
        this.service = service;
    }

    @GetMapping("/user1/register")
    public String register(){
        return "/user1/register"; // forward (포워드)
        // 리턴해주는 게 포워드 해주는 거임
    }
    // form 으로 보내주는 것은 무조건 post로 (정보 보내주고)
    @PostMapping("/user1/register")
    public String register(User1DTO dto){
        System.out.println(dto);
        // 등록
        service.insertUser1(dto);
        // 리다이렉트
        return "redirect:/user1/list";
    }
    // 리스트는 정보를 그냥 뿌려주기만 하면 되니까 GetMapping (doget)
    // list.jsp 에는 form 이 없음
    @GetMapping("/user1/list")
    public String list(Model model){
        // 조회
        List<User1DTO> users = service.selectUser1s();
        System.out.println(users);
        // 모델참조(Controller 데이터를 View에서 참조)
        model.addAttribute("users", users);
        return "/user1/list";
    }

    // 수정 해야 할 회원 정보를 들고와야하니까 GetMapping
    @GetMapping("/user1/modify")
    public String modify(@RequestParam("uid") String uid, Model model){
        System.out.println("uid : " + uid);
        // 수정 회원 조회
        User1DTO user = service.selectUser1(uid);
        // 모델 참조
        model.addAttribute(user); // 타입명으로 저장(소문자 시작 -> user1DTO)
        //model.addAttribute("user", user);
        //                   "key",  value
        return "/user1/modify"; // forward 해줌
    }

    @PostMapping("/user1/modify") // User1DTO user 앞에 @ModelAttribute
    public String modify(@ModelAttribute User1DTO dto){ // @ModelAttribute 생략가능
        System.out.println(dto);
        // 수정
        service.updateUser1(dto);
        // 리다이렉트
        return "redirect:/user1/list"; //앞에 WEB-INF/view 뒤에 .jsp // 뷰 리졸버가 붙여줌
    }

    @GetMapping("/user1/delete")
    public String delete(@RequestParam("uid") String uid){ // @RequestParam("uid") 생략하면 안됨
        System.out.println("uid : " + uid);

        // 삭제
        service.deleteUser1(uid);

        // 리다이렉트
        return "redirect:/user1/list";
    }

}
