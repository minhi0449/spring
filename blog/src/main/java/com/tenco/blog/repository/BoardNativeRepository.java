package com.tenco.blog.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository // IoC 대상
public class BoardNativeRepository {
    // JPA 의 핵심 인터페이스
    // 데이터베이스와의 모든 작업을 담당
    private EntityManager em;

    // 생성자를 확인해서 자동으로 EntityManager 객체를 주입시킨다.
    // DI 처리
    public BoardNativeRepository(EntityManager em){
        this.em = em;
        System.out.println("=== BoardNativeRepository 생성됨, EntityManager 주입 완료 ===");
    }

    // 트랜잭션 처리
    public void save(String title, String content, String username){
        System.out.println("=== Repository: save 메서드 실행 시작 ===");
        System.out.println("전달받은 파라미터 - title: " + title + ", content: " + content + ", username: " + username);

        Query query = em.createNativeQuery("insert into board_tb(title, content, username, created_at)" +
                "values(?, ?, ?, now())");

        System.out.println("=== Repository: Native Query 생성 완료 ===");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, username);
        System.out.println("=== Repository: 파라미터 바인딩 완료 ===");

        int result = query.executeUpdate();
        System.out.println("=== Repository: executeUpdate 완료, 영향받은 행 수: " + result + " ===");
    }
}
