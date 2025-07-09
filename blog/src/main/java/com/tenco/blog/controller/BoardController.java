package com.tenco.blog.controller;

import com.tenco.blog.repository.BoardNativeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // IoC 대상 - 싱글톤 패턴으로 관리됨
@RequiredArgsConstructor
public class BoardController {

    private final BoardNativeRepository boardNativeRepository;

    @GetMapping({"/", "/index"})
    public String index(){
        // prefix: /templates/
        // return: index
        // suffix: .mustache
        // # 기본 경로를 src/main/resources/templates/index/mustache
        return "index";
    }

    // username, title, content <--- DTO 받는 방법, 기본 데이터 타입 설정
    // form 태그에서 넘어오는 데이터 받기
    // form 태그에서 name 속성에 key 값 동일해야 함
    // http://localhost:8080/board/save
    // 스프링부트 기본 파싱 전략 : key-value (form)
    @PostMapping("/board/save")
    public String save(@RequestParam("username") String username,
                       @RequestParam("title") String title,
                       @RequestParam("content") String content){

        System.out.println("=== Controller: 폼 데이터 수신 ===");
        System.out.println("title : " + title);
        System.out.println("content : " + content);
        System.out.println("username : " + username);


        return "redirect:/";
    }

    @GetMapping("/board/save-form")
    public String saveForm(){
        // /templates//board
        // /templates/board/
        return "board/save-form";
    }

    /**
     * 상세보기 화면 요청
     * board/1
     */
    @GetMapping("/board/{id}")
    public String detail(@PathVariable(name = "id") Integer id){
        // URL 에서 받은 id 값을 사용해서 특정 게시글 상세보기 조회
        // 실제로는 이 id 값으로 데이터베이스에 있는 게시글 조회하고,
        // 머스태치 파일로 데이터를 내려 주어야 함 (Model)
        return "board/detail";
    }
}
