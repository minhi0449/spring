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

    /*
        Mapper 인터페이스 : 비즈니스 로직 (Service)와 SQL을 분리해서 유지보수성을 높임
        DTO : 데이터를 객체 형태로 담아 코드 간 깔끔한 전달 가능
        @Mapper : Spring이 이 인터페이스를 Mapper 로 인식하고, 자동으로 구현체를 생성해줌
        메서드 이름 : XML 파일의 SQL과 매칭되므로 이름을 일관성있게 정해야 함

        이 파일은 DB와 직접 연결되는 Mapper 인터페이스
        실제 쿼리(SQL)은 .xml 에 있고, 이 인터페이스는 메서드 "이름"과 "파라미터"만 정의
        데이터 흐름은 항상 Controller --> Service --> Mapper --> DB 순서로 이해하기
        MyBatis를 쓸 떄는 @Mapper, DTO 클래스, XML 매핑이 필수
     */
}
