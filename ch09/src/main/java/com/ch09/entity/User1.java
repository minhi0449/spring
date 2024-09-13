package com.ch09.entity;

import com.ch09.dto.User1DTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

// Entity는 @Setter 안씀
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity                 // 엔티티 객체 정의
@Table(name = "user1")  // 매핑 테이블 설정
public class User1 {

    @Id
    private String uid;
    private String name;
    private String birth;
    private String hp;
    private int age;

    public User1DTO toDTO(){
        return User1DTO.builder()
                .uid(uid)
                .name(name)
                .birth(birth)
                .hp(hp)
                .age(age)
                .build();
    }
}