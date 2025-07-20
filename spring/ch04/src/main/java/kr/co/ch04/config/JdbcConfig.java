package kr.co.ch04.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/*
    JDBC 데이터베이스 연결 설정을 담당하는 Configuration 클래스
    스프링이 애플리케이션 시작 시 이 클래스를 읽어서 데이터베이스 연결 설정을 적용합니다.
 */
@Configuration // 스프링에게 "이 클래스는 설정 정보를 담고 있어요" 라고 알려주는 어노테이션
public class JdbcConfig {

    /**
     * 데이터베이스 연결 풀(Connection Pool)을 생성하고 설정하는 메서드
     * 연결 풀은 미리 데이터베이스 연결을 여러 개 만들어두고 재사용하는 시스템입니다.
     *
     * @return DataSource 데이터베이스 연결을 관리하는 객체
     */
    @Bean // 스프링 컨테이너가 이 메서드의 반환값을 관리하도록 등록하는 어노테이션
    public DataSource getDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/studydb");
        dataSource.setUsername("root");
        dataSource.setPassword("Rlaalsgml312!");

        dataSource.setMaxTotal(13); // 최대 커넥션 갯수 (동시에 13명까지 데이터베이스 사용 가능)
        dataSource.setMaxIdle(13); // 최대 유휴 커넥션 갯수

        return dataSource;
    }

    public JdbcTemplate getJdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
