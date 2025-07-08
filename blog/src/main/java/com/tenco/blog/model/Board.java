package com.tenco.blog.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "board_tb")
@Entity
public class Board {
    // @Id 이 필드가 기본키(Primary Key) 임을 나타냄
    // IDENTITY 전략 : 데이터베이스의 기본 전략을 사용한다. --> Auto_Increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 별도 어노테이션이 없으면 필드명이 컬럼명이 됨
    private String title;
    private String content;
    private String username;
    private String createdAt; // created_at (스네이크 케이스로 자동 변환
}
