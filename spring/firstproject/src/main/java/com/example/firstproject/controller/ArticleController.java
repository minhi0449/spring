package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j // 로깅 기능을 위한 어노테이션 추가
@Controller
public class ArticleController {

    @Autowired // 스프링 부트가 미리 생성해 놓은 레파지토리 객체 주입(DI)
    private ArticleRepository articleRepository;
    /*
        스프링부트에서는 객체를 만들지 않아도 됩니다.
        스프링부트가 알아서 객체를 만들기 때문입니다.
        @Autowired 어노테이션을 붙이면 스프링부트가 미리 생성해 놓은 객체를 가져다가 연결해줍니다.

        @Autowired 는 스프링 부트에서 제공하는 어노테이션으로 이를 컨트롤러의 필드에 붙이면
        스프링 부트가 만들어 놓은 객체를 가져와 주입해 줍니다.
        이를 의존성 주입(DI, Dependency Injection) 이라고 합니다.
     */

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){ // 폼 데이터를 DTO로 받기
        log.info(form.toString());
        // System.out.println(form.toString()); // DTO에 폼 데이터가 잘 담겼는지 확인

        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString());
        // System.out.println(article.toString()); // DTO가 엔티티로 잘 변환되는지 확인 출력

        // 2. 레피자토리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article); // article 엔티티를 저장해 saved 객체에 반환
        log.info(saved.toString());
        // System.out.println(saved.toString()); // article이 DB에 잘 저장되는지 확인 출력
        return "";
    }

}
