package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;

public class ArticleForm {
    private String title;   // 제목을 받을 필드
    private String content; // 내용을 받을 필드

    // 데이터를 잘 받았는지 확인할 toString() 메서드 추가
    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Article toEntity() {
        return new Article(null, title, content);
    }
}
