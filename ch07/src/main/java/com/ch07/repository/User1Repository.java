package com.ch07.repository;

import com.ch07.entity.User1;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// JpaRepository<T, ID> 사용하는 엔티티 타입과 해당 엔티티 @ID 컬럼 속성 타입 선언
@Repository
public interface User1Repository extends JpaRepository<User1, String> {
    // interface 상속 문법
    // JpaRepository<T(사용한 Entity (User1 엔티티)), ID(속성의 타입) (User1의 타입)>
    // 어노테이션이랑 클래스이름이랑 동일
    //  기본 CRUD 메서드 --> JPA 는 필요없음 JpaRepository 가 전부 지원 쿼리 작업 필요없음

    // 사용자 정의 쿼리 메서드
    // findUser1ByUid 규칙
    public User1 findUser1ByUid(String Uid);
    public List<User1> findUser1ByName(String name);
    public List<User1> findUser1ByNameNot(String name);

    public User1 findUser1ByUidAndName(String Uid, String name);
    public List<User1> findUser1ByUidOrName(String Uid, String name);

    public List<User1> findUser1ByAgeGreaterThan(int age);
    public List<User1> findUser1ByAgeGreaterThanEqual(int age);
    public List<User1> findUser1ByAgeLessThan(int age);
    public List<User1> findUser1ByAgeLessThanEqual(int age);
    public List<User1> findUser1ByAgeLessBetween(int low, int high);


    public List<User1> findUser1ByNameLike(String name);
    public List<User1> findUser1ByNameContains(String name); // 포함되는 문자열
    public List<User1> findUser1ByNameStartingWith(String name); // 종료되는 문자열
    public List<User1> findUser1ByNameEndsWith(String name); //


    // 이런 메서드를 보면서 이렇게 규칙을 짜면 되는 구니 하면 됨
    // sql 문 대신에 이거 적어주는 거임 ! 우리가 막 적는 게 아니고 나름 전부 다 규칙이 다 있음
    public List<User1> findUser1ByOrOrderByName();
    public List<User1> findUser1ByOrOrderByAgeAsc();
    public List<User1> findUser1ByOrOrderByAgeDesc();
    public List<User1> findUser1ByAgeGreaterThanOrderByAgeDesc(int age);

    public int countUser1ByUid(String uid);
    public int countUser1ByName(String name);

    // JPQL (네이티브 sql) : JPA Native SQL (순수 sql 그 자체를 처리해야 하는 경우가 생김 = join)
    @Query("select u1 from User1 as u1 where u1.age < 30")
    public List<User1> selectUser1UnderAge30();
    // 여기서 User1 이름은 Entity 이름
    // 별칭 줘야 함 -> 문법 임

    // 메서드 호출할 떄 받는 인자값
    @Query("select u1 from User1 as u1 where u1.name = ?1")
    public List<User1> selectUser1WhereName(String name);

    // 매개변수를 jpql 변수로
    @Query("select u1 from User1 as u1 where u1.name = :name")
    public List<User1> selectUser1WhereParam(@Param("name") String name);

    // 정의 aptjem selectFromParentJoinChild
    // JPA 쓸 지, MyBatis 쓸 지는 회사 환경에 따라서 다를듯
    @Query("select p, c from Parent as p " +
            "join Child as c on p.pid = c.parent " +
            " where p.pid = :pid")
    public List<Object[]> selectFromParentJoinChild(@Param("pid") String pid);



}
