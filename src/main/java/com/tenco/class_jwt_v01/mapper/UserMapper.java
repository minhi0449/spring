package com.tenco.class_jwt_v01.mapper;

import com.tenco.class_jwt_v01.domain.User;
import org.apache.ibatis.annotations.Mapper;

/*
    2025.02.25 (화) - User 매퍼 설계
 */

@Mapper // 반드시 필요함 (마이바티스 사용)
public interface UserMapper {

    void save(User user);
    User findByusername(String username);
    void updateRefreshToken(User user); // 리프레시 토큰 DB 에 저장하기로 함

}
