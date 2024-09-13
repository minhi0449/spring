package com.ch07.service;

import com.ch07.dto.User1DTO;
import com.ch07.entity.User1;
import com.ch07.repository.User1Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class User1Service {

    // 생성자 주입
    private final User1Repository user1Repository;

    //
    public void insertUser1(User1DTO user1DTO) {

        // DTO를 Entity로 변환
        User1 entity = user1DTO.toEntity();

        // Entity 저장(데이터베이스 Insert)
        user1Repository.save(entity);

    }

    public User1DTO selectUser1(String uid) {

        /*
            ** Optional
            - null 체크 검정을 손쉽고 안정하게 처리하기 위한 클래스
            - Spring JPA find~ 메서드의 결과 타입(포장 되어있음)
         */
       Optional<User1> opt = user1Repository.findById(uid);

        // Entity 존재 여부 확인 후 Optional 타입으로 정의된 Entity 가져오기
        if (opt.isPresent()) {
            User1 user1 = opt.get(); // 존재하는지 먼저 확인하는 거임 (안전하게 하는 거)
            // Entity 를 DTO로 변환해서 반환
            return user1.toDTO();
        }
        return null;
    }

    public List<User1DTO> selectUser1s() {
        
        // Entity 전체 조회
        List<User1> user1s = user1Repository.findAll();
        /*
        List<User1DTO> user1DTOS = new ArrayList<>();
        // user1s Entity 리스트다 ->  뭔가 반복 처리하는 게 필요함
        // for(외부 반복자)를 이용한 Entity 리스트를 DTO리스트로 변환
        // 시스템 성능에 좋지 않음 (권장x) 트래픽이 몰린다는 과정 하에 시스템에 나쁜 영향 끼침
        // 
        for (User1 user1 : user1s) {
            user1DTOS.add(user1.toDTO());
        }
        */

        // List stream(외부 반복자)를 이용한 Entity 리스트를 DTO 리스트로 변환
        // 함수형 프로그래밍
        // map() (통으로 외우기) 리스트에 있는 거 일괄 처리
        //
        List<User1DTO> users = user1s
                                .stream()
                                .map(entity -> entity.toDTO())
                                .collect(Collectors.toList());

        // return User1 못함
        // return user1s 못함
        return users;

    }

    public void updateUser1(User1DTO user1DTO) {
        // 존재 여부 먼저 확인
        // Entity 존재 여부 확인
        // 존재하면 true
        boolean result = user1Repository.existsById(user1DTO.getUid());

        if(result){
            // DTO를 Entity로 변환
            User1 entity = user1DTO.toEntity();

            // Entity 수정 (데이터베이스 Update)
            user1Repository.save(entity);
        }

        // save 함수
        user1Repository.save(user1DTO.toEntity());
    }

    public void deleteUser1(String uid) {
        // Entity 삭제 (데이터베이스 Delete)
        user1Repository.deleteById(uid);
    }

}
