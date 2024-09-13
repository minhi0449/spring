package com.ch07.repository;

import com.ch07.entity.Child;
import com.ch07.entity.Parent;
import com.ch07.entity.User1;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.thymeleaf.spring6.context.SpringContextUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class User1RepositoryTest {

    //@Autowired 이렇게 하면 안됨 이유는 모름
    //private final User1Repository user1Repository;

    @Autowired
    private User1Repository user1Repository;

    @Test
    void findUser1ByUid() {
        User1 entity = user1Repository.findUser1ByUid("A101");
        // entity 결과 출력
        System.out.println(entity);
    }

    @Test
    void findUser1ByName() {
        // 이름가지고 조회하는 거 
       List<User1> users = user1Repository.findUser1ByName("김춘추");
       System.out.println(users);
    }

    @Test
    void findUser1ByNameNot() {
        List<User1> users = user1Repository.findUser1ByNameNot("김춘추");
        System.out.println(users);
    }

    @Test
    void findUser1ByUidAndName() {
        List<User1> users = (List<User1>) user1Repository.findUser1ByUidAndName("A101", "김유신");
        System.out.println(users);
    }

    @Test
    void findUser1ByUidOrName() {
        List<User1> users = user1Repository.findUser1ByUidOrName("A102", "김춘추");
        System.out.println(users);
    }

    @Test
    void findUser1ByAgeGreaterThan() {
       List<User1> users = user1Repository.findUser1ByAgeGreaterThan(21);
       System.out.println(users);
    }

    @Test
    void findUser1ByAgeGreaterThanEqual() {
        List<User1> users = user1Repository.findUser1ByAgeGreaterThanEqual(23);


    }

    @Test
    void findUser1ByAgeLessThan() {

    }

    @Test
    void findUser1ByAgeLessThanEqual() {

    }

    @Test
    void findUser1ByAgeLessBetween() {

    }

    @Test
    void findUser1ByNameLike() {
    }

    @Test
    void findUser1ByNameContains() {
    }

    @Test
    void findUser1ByNameStartingWith() {
    }

    @Test
    void findUser1ByNameEndsWith() {
    }

    @Test
    void findUser1ByOrOrderByName() {
    }

    @Test
    void findUser1ByOrOrderByAgeAsc() {
    }

    @Test
    void findUser1ByOrOrderByAgeDesc() {
    }

    @Test
    void findUser1ByAgeGreaterThanOrderByAgeDesc() {
    }

    @Test
    void countUser1ByUid() {
    }

    @Test
    void countUser1ByName() {
    }

    @Test
    void selectUser1UnderAge30(){

    }

    @Test
    void selectUser1WhereName(){

    }

    @Test
    void selectUser1WhereParam(){

    }

    @Test
    void selectFromParentJoinChild(){
        // 리스트 안에 배열
        List<Object[] > list = user1Repository.selectFromParentJoinChild("P101");

        System.out.println(list.toString());

        for(Object[] obj : list){
            System.out.println();

            Parent parent = (Parent) obj[0];
            Child child = (Child) obj[1];

            System.out.println(parent);
            System.out.println(child);
        }
    }


}