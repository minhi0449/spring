package com.ch06.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor // 매개변수가 없는 생성자 초기화 --> public User1DTO(){}
@AllArgsConstructor // public User1DTO(String uid, String name, String birth){}
@Builder // 문법 지원해줌
public class User1DTO {

    private String uid;
    private String name;
    private String birth;
    private String hp;
    private int age;




}
