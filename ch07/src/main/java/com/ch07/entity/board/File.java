package com.ch07.entity.board;


import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"file", "comment"}) // 먼저 속성명, 파일 (toString을 제외시킨다) user 단방향이라서 안 써도 test 가능
@Builder
@Entity
@Table(name = "board_file")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fno;

    private String oName;
    private String sName;

    // file 기준으로 Article 이랑도 관계 설정해줘야 함
    // file 에서도 관계설정 해줘야 함
    // 웬만하면 LAZY로 설정 다대일 = N:1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ano")
    private Article article;

}
