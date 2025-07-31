package com.ch05.dao;

import com.ch05.dto.User1DTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

// MyBatis Scanning 을 위해 @Mapper 선언
@Mapper
public interface User1Mapper {

    // 사용자 추가 (Create)
    public void insertUser1(User1DTO user1DTO);

    // 전체 사용자 목록 조회 (Read - List)
    public List<User1DTO> selectUser1s();

    // 특정 사용자 1명 조회 (Read - One)
    public User1DTO selectUser1(String uid);

    // 사용자 정보 수정 (Update)
    public void updateUser1(User1DTO user1DTO);

    // 사용자 삭제 (Delete)
    public void deleteUser1(String uid);
}
