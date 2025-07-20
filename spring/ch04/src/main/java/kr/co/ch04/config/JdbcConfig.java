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
        // setMaxIdle : 연결 풀에서 유지할 최대 유휴(사용하지 않는) 연결 개수 설정
        // 사용이 끝난 연결을 바로 버리지 않고 재사용을 위해 13개까지 보관

        // 설정이 완료된 DataSource 객체를 스프링 컨테이너에 반환
        // 이제 다른 클래스에서 @Autowired 로 이 객체를 주입받아 사용할 수 있음
        return dataSource;
    }

    /**
     * JdbcTemplate 객체를 생성하는 메서드
     * JdbcTemplate 은 데이터베이스 작업(SELECT, INSERT, UPDATE, DELETE) 을
     * 쉽게 할 수 있도록 도와주는 스프링의 도구
     *
     * @param dataSource // 위에서 생성한 DataSource 객체가 스프링에 의해 자동 주입
     * @return JdbcTemplate 데이터베이스 작업을 위한 템플릿 객체
     */
    @Bean // 이 메서드의 반환값도 스프링 컨테이너가 관리하도록 등록
    public JdbcTemplate getJdbcTemplate(DataSource dataSource){
        // 주입 받은 DataSource 를 사용해서 JdbcTemplate 객체 생성
        // 이렇게 생성된 JdbcTemplate 으로 SQL 쿼리를 실행할 수 있음
        return new JdbcTemplate(dataSource);
    }
}
