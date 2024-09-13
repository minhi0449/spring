package com.ch09.dto;

import com.ch09.entity.User1;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User1DTO {
    // message 형식이 맞지 않을 경우 사용자에게 표시되는 메시지
    @NotBlank // Null, "", " " 모든 허용 안 함
    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "영어 소문자와 숫자로 최소 4~10자 입력")
    private String uid; // 반드시 입력

    @NotEmpty // " " 은 허용 (공백 허용) / null, "" 둘 다 허용 안 함
    @Pattern(regexp = "^[가-힣]{2,10}$", message = "이름은 한글 2~10자 입력")
    private String name;

    @NotNull // null 허용 안 함
    private String birth;

    private String hp;

    @Min(1)
    @Max(100)
    private int age;

    @Email
    private String email;

    public User1 toEntity() {
        return User1.builder()
                .uid(uid)
                .name(name)
                .birth(birth)
                .hp(hp)
                .age(age)
                .build();
    }
}