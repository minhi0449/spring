package com.ch07.dto;


import lombok.Data;

@Data
public class CustomerDTO {

    private String custId;
    private String name;
    private int age;
    private String hp;
    private String addr;
    private String regDate;

    // 추가 필드
    private long orderCount;
}
