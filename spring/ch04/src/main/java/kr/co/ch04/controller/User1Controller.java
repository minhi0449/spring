package kr.co.ch04.controller;

import kr.co.ch04.dto.User1DTO;
import kr.co.ch04.service.User1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class User1Controller {

    private User1Service user1Service;

    @Autowired
    public User1Controller(User1Service user1Service){
        this.user1Service = user1Service;
    }

    @GetMapping("/user1/register")
    public String register(){
        System.out.println("[controller] - register(get)");
        return "/user1/register"; // forward (포워드)
        // 리턴해주는게 포워드 해주는 거임
    }

    @PostMapping("/user1/register") // form 으로 보내주는 것은 무조건 post 로 정보 보내중
    public String register(User1DTO dto){
        System.out.println("[controller] - register(post)");
        System.out.println("dto : " + dto);
        // 등록
        user1Service.insertUser1(dto);
        // 리다이렉트
        return "redirect:/user1/list";
    }

    @GetMapping("/user1/list")
    public String list(Model model){
        System.out.println("[controller] - list(get)");
        // 조회
        List<User1DTO> users = user1Service.selectUser1s();
        System.out.println("[controller] users : " + users);
        model.addAttribute("users", users);
        return "/user1/list";
    }

    // 수정
    // 수정해야 할 회원정보를 들고와야 하니까 GetMapping
    @GetMapping("/user1/modify")
    public String modify(@RequestParam("uid") String uid, Model model){
        System.out.println("[controller] - modify(get)");
        System.out.println("uid : " + uid);

        // 수정회원 조회
        User1DTO user = user1Service.selectUser1(uid);

        // 모델 참조
        model.addAttribute(user); // 타입명으로 저장
        return "/user1/modify";
    }

    @PostMapping("/user1/modify")
    public String modify(@ModelAttribute User1DTO dto){
        System.out.println("[controller] - modify(post)");
        System.out.println("dto : " + dto);
        user1Service.updateUser1(dto);
        return "redirect:/user1/list"; // 앞에 WEB-INF/view 뒤에 .jsp --> 뷰 리졸버가 붙여줌
    }

    // 삭제
    @GetMapping("/user1/delete")
    public String delete(@RequestParam("uid") String uid){
        System.out.println("[controller] - delete(get)");

        // 삭제
        user1Service.deleteUser1(uid);

        // 리다이렉트
        return "redirect:/user1/list";
    }

}
