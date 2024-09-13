package com.ch07.dto;


import com.ch07.entity.User1;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User1DTO {
    private String uid;
    private String name;
    private String birth;
    private String hp;
    private String age;


    // Entity 변환 메서드
    public User1 toEntity(){
        // Builder 방식 생성
        return User1.builder()
                .uid(uid)
                .name(name)
                .birth(birth)
                .hp(hp)
                .age(age)
                .build();
    }
}
