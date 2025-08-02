package com.ch05.service;

import com.ch05.dao.User1Mapper;
import com.ch05.dto.User1DTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User1Service : 비즈니스 로직을 처리하는 서비스 계층
 * Controller 와 Mapper(DAO) 사이의 중간 역할을 담당
 * MVC 패턴에서 Service(Business Logic) 계층에 해당
 */

@Service
public class User1Service {

    // User1Mapper 객체를 저장할 변수 (데이터베이스 작업을 담당하는 Mapper)
    private final User1Mapper user1Mapper;

    /**
     * 생성자 - 의존성 주입을 통해 User1Mapper 객체를 받아옴
     * @param user1Mapper
     */
    public User1Service(User1Mapper user1Mapper){
        // 받아온 매퍼 객체를 멤버변수에 저장
        this.user1Mapper = user1Mapper;
    }

    /**
     * 새로운 사용자를 데이터베이스에 저장하는 메서드
     * Controller 에서 받은 사용자 정보를 검증하고 Mapper 에게 전달
     * @param user1DTO (저장할 사용자 정보가 담긴 Data Transfer Object)
     */
    public void insertUser1(User1DTO user1DTO){
        // 여기서 필요하다면 비즈니스 로직 추가 가능 (예: 데이터 검증, 암호화 등)
        // 현재는 단순히 Mapper 에게 저장 작업을 위임
        this.user1Mapper.insertUser1(user1DTO);
    }

    /**
     * 모든 사용자 목록을 조회하는 메서드
     * 데이터베이스에서 모든 사용자 정보를 가져와서 Controller 에게 반환
     * @return 모든 사용자 정보가 담긴 List<User1DTO>
     */
    public List<User1DTO> selectUser1s() {
        // Mapper 에게 '모든 사용자 데이터를 가져와줘!'라고 요청하고 결과를 그대로 반환
        return user1Mapper.selectUser1s();
    }

    /**
     * 특정 사용자 한 명의 정보를 조회하는 메서드
     * 사용자 ID(uid)를 받아서 해당하는 사용자 정보를 조회
     * @param uid : 조회할 사용자의 ID (Primary Key)
     * @return 해당 사용자의 정보가 담긴 User1DTO 객체
     */
    public User1DTO selectUser1(String uid){
        return user1Mapper.selectUser1(uid);
    }

    public void updateUser1(User1DTO user1DTO){
        user1Mapper.updateUser1(user1DTO);
    }

    public void deleteUser1(String uid){
        user1Mapper.deleteUser1(uid);
    }
}
