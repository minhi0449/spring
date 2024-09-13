package com.ch07.entity;


import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

// Entity는 @Setter 안 씀 (보안상 문제)

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity                // 엔티티 객체 정의 (테이블이 매핑되는 값)
@Table(name = "parent") // 매핑 테이블 설정
public class Parent {

    @Id
    private String pid;
    private String name;
    private String birth;
    private String addr;

}
