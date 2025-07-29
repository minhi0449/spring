package com.ch05.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import javax.sql.DataSource;

/**
 * MyBatis 설정 클래스
 * 이 클래스는 MyBatis 와 스프링을 연동하기 위한 모든 설정을 담고 있음
 */
@Configuration
@MapperScan(basePackages = {"com.ch05"}) // com.ch05 패키지에서 @Mapper 가 붙은 인터페이스들을 자동으로 찾아서 등록
public class MybatisConfig {
    /**
     * SqlSessionFactory 빈 생성 메서드
     * MyBatis 의 핵심인 SqlSessionFactory 를 생성
     *
     * @param dataSource // 데이터베이스 연결 정보가 담긴 객체 (스프링이 자동으로 주입해줌)
     * @return 설정이 완료된 SqlSessionFactory 객체
     * @throws Exception // 설정 과정에서 발생할 수 있는 예외
     */
    @Bean // 스프링 컨테이너가 이 메서드를 실행해서 객체를 생성하고 관리하라는 표시
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {

        // SqlSessionFactory 를 쉽게 만들어주는 도우미 객체 생성
        // 복잡한 설정 과정을 간단하게 만들어주는 스프링의 편의 기능
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        // 어느 데이터베이스와 연결한지 정보를 설정
        sqlSessionFactoryBean.setDataSource(dataSource);

        // SQL 쿼리가 적혀있는 XML 파일들의 위치를 설정
        // classpath*: --> 프로젝트의 모든 클래스패스에서 찾기
        // mapper/ --> mapper 폴더 안에서
        // *.xml --> 모든 xml 파일들
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResource("classpath*:mapper/*.xml"));

        // 설정이 완료된 SqlSessionFactory 객체를 반환
        // 이제 이 Factory로 실제 데이터베이스 작업을 할 수 있는 SqlSession 을 만들 수 있음
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * SqlSessionTemplate 빈 생성 메서드
     * 실제로 SQL을 실행하는 템플릿 객체를 생성
     *
     * @param sqlSessionFactory
     * @return
     */
    @Bean // 스프링이 이 객체를 관리하라는 표시
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
        // SqlSessionTemplate 생성 및 반환
        // 이 객체는 Thread-Safe 하므로 여러 스레드가 동시에 사용해도 안전
        // 실제로 Mapper 인터페이스의 메서드가 호출될 때 이 템플릿이 SQL을 실행
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
