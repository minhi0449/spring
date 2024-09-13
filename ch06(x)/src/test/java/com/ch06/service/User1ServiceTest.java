
package com.ch06.service;

import com.ch06.dto.User1DTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


// 순서 정하기
// 순서대로 run as 가 안됐음





@SpringBootTest
class User1ServiceTest {

    @Autowired
    private User1Service user1Service;

    @Test
    void insertUser1() {
        // 테스트 정의 : given - when - then 패턴

        // given : 테스트를 준비, 샘플 데이터 생성
        User1DTO sample = User1DTO.builder()
                .uid("a202")
                .name("김유신")
                .birth("1999-01-02")
                .hp("010-2222-1010")
                .age(22)
                .build();

        // when : 테스트를 진행
        user1Service.insertUser1(sample);

        // then : 테스트 검증, Assert 단정문을 이용해 결과 출력
        User1DTO expected = user1Service.selectUser1(sample.getUid());
        Assertions.assertEquals(expected.toString(), sample.toString());
    }

    @Test
    void selectUser1() {

        // given
        String uid = "a202";

        // when
        User1DTO expected = user1Service.selectUser1(uid);

        // then
        Assertions.assertEquals(expected.getUid(), uid);

    }

    @Test
    void selectUser1s() {
    }

    @Test
    void updateUser1() {

        // given : 테스트를 준비, 샘플 데이터 생성
        User1DTO sample = User1DTO.builder()
                .uid("a202")
                .name("김유진")
                .birth("1999-01-22")
                .hp("010-2222-2222")
                .age(22)
                .build();

        //
        user1Service.updateUser1(sample);


        // then
        user1Service.selectUser1(sample.getUid());
        Assertions.assertEquals("010-2222-2222", sample.getHp());

    }

    @Test
    void deleteUser1() {

        String uid = "a202";
        user1Service.deleteUser1(uid);


        // null 체크


        // 삭제하고 나면 조회가 안될 거 아니야?
        User1DTO expected = user1Service.selectUser1(uid);
        Assertions.assertEquals(expected);



    }
}

// test case를 한 번에 실행해서 하나라도 실패가 있으면 안 되겠지