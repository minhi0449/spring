package com.ch07.entity.board;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "board_article")
public class Article {
    // Article Entity가 기준!
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    private String title;
    private String content;

    /*
        @ManyToOne
         - N:1 관계설정, 기본 패치모드 E~
         - Article 엔티티와 User 엔티티 간의 관계와 방향성를 고려해서 연관관계 설정
         - Article 엔티티가 조회될 때 User 엔티티도 같이 조회

        @JoinColumn
         - User 엔티티가 참조되는 테이블 컬럼명 설정
         - name 속성은 컬럼명
     */

    // 하나의 글 = 하나의 작성자 (밖에 없다)
    // 하나의 작성자 = 여러 개의 글 (쓸 수 있다)
    @ManyToOne(fetch = FetchType.LAZY) // 필요한 시점에 로딩되도록
    @JoinColumn(name = "writer")
    private User user; // user 라는 속성이 어떻게 컬럼으로 만들어져 있냐

    // Many니까 List
    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
    //private List<File> file;
    
    // 안 해주면 큰일 나는 듯
    // OneToMany는 양방향이라고 생각해줘야 함
    // mappedBy = "article" --> 현재 기준이 되고 있는 table
    // Article하고 Comment 관계
    // 하나의 글은 여러 건의 댓글을 작성할 수 있음

    /*
        @OneToMany
         - 1:N 관계설정, 일반적으로 양방향 관계 설정, 참조타입이 List
         - Article 엔티티와 File 엔티티 간의 관계와 방향성를 고려해서 연관관계 설정
         - Article(1) 엔티티가 조회될 때 File(N) 엔티티도 같이 조회
         - mappedBy 속성은 양방향 관계에서 기준이 되는 속성을 설정, FK가 되는 엔티티 속성

        @Transactional
         - 양방향으로 처리되는 연관관계에서 다수의 SELECT를 트랜잭션으로 수행
         - 하나의 SELECT는 하나의 세션처리로 트랜잭션으로 처리하지 않으면 no Session 에러 발생
         - 트랜잭션으로 처리하기 위해서 처리 메서드에 @Transactional 선언

        @ToString(exclude="제외할 속성")
         - 엔티티간 양방향 관계설정에서 toString() 호출할 경우 무한순환 호출이 실행
         - 무한순환 호출이 발생하면 StackOverflow 에러 발생
         - 양방향으로 관계설정된 엔티티에서 어느 한쪽을 ToString에서 제외

     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
    private  List<Comment> comment;

    @CreationTimestamp
    private LocalDateTime rdate;



}
