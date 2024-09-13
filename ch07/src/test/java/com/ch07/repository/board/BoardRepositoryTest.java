package com.ch07.repository.board;

import com.ch07.entity.board.Article;
import com.ch07.entity.board.Comment;
import com.ch07.entity.board.File;
import com.ch07.entity.board.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired private ArticleRepository articleRepository;
    @Autowired private CommentRepository commentRepository;
    @Autowired private FileRepository fileRepository;
    @Autowired private UserRepository userRepository;

    // 테스트 1 - 사용자 등록
    @Test
    void insertUserTest(){
        User user = User.builder()
                        .uid("a101")
                        .name("김유신")
                        .nick("유신101")
                        .email("yousin@naver.com")
                        .build();

        userRepository.save(user);



    }

    // 테스트 2 - 글 등록
    // Entity 연관 관계 -> User가 등록 되어야만 Article 등록 가능
    @Test
    void insertArticleTest(){
        User user = User.builder()
                .uid("a101")
                //.uid("a102")로 테스트 해보기 ! -> Error 날 거임
                .build();

        Article article = Article.builder()
                        .title("제목1 입니다.")
                        .content("내용 입니다.")
                        .user(user)
                        .build();

        articleRepository.save(article);
    }

    // 테스트 3 - 댓글 등록
    @Test
    void insertCommentTest(){
        User user = User.builder()
                .uid("a102") // a102 - 댓글 사용자로 등록하기로 함
                .build();

        Article article = Article.builder()
                .no(1) // 1번 글
                .build();

        Comment comment = Comment.builder()
                            .content("댓글1 입니다.")
                            .user(user)
                            .article(article)
                            .build();

        commentRepository.save(comment);

    }

    // 테스트 4 - 파일 등록 테스트
    @Test
    void insertFileTest(){

        Article article = Article.builder()
                .no(1) // 1번 글
                .build();

        File file = File.builder()
                .oName("테스트1.txt")
                .sName("ABC123.txt")
                .article(article)
                .build();

        fileRepository.save(file);


    }


    // 테스트 5 - 글 조회
    @Transactional
    @Test
    void selectArticlesTest(){
        List<Article> articles = articleRepository.findAll();
        //System.out.println(articles);

        for(Article article : articles){

            List<Comment> comments = article.getComment();
            List<File> files = article.getFile();

            System.out.println(comments);
            System.out.println(files);

        }


    }



}












