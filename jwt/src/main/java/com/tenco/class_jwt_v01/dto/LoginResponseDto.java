package com.tenco.class_jwt_v01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
// @NoArgsConstructor 여기서는 기본 생성자 잘 사용하지 않음
public class LoginResponseDto {
    // 엑세스 토큰
    private String accessToken;
    // 리프레시 토큰을 던져줘야 함
    private String refreshToken;
    // 사용자 이름
    private String username;
    // 나머지 추가적인 정보가 필요하다면 토큰을 확인하고 별도에 API로 제공한다.
    // 한 번에 다 담아서 던지는 게 아니고,

}
