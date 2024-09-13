package com.ch06.service;

import com.ch06.dto.User1DTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor // 반드시 생성자를 정의
@Service
public class User1Service {
    // final 로 하면 생성자로 초기화 해라 생성자 반드시 나와야 함
    private final User1Mapper user1Mapper;

    @Autowired
    public User1Service(User1DTO user1DTO) {
        this.user1Mapper = user1DTO;
    }

    @test
    public void insertUser1(User1DTO user1DTO){
        this.user1Mapper.insertUser1(user1DTO);
    };

    public List<User1DTO> selectUser1s(){
        return user1Mapper.selectUser1s();
    };

    public User1DTO selectUser1(String uid){
        return user1Mapper.selectUser1(uid);
    };

    public void updateUser1(User1DTO user1DTO){
        user1Mapper.updateUser1(user1DTO);
    };

    public void deleteUser1(String uid){
        user1Mapper.deleteUser1(uid);
    };

}
