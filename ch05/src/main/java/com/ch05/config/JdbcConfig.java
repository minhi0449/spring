package com.ch05.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {

    @Bean
    public DataSource dataSource() {

        // 커넥션 풀 객체 = dataSource
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/studydb"); // localhost
        dataSource.setUsername("root");
        dataSource.setPassword("1234");

        dataSource.setMaxTotal(13); // 최대 연결 커넥션 객수
        dataSource.setMaxIdle(13);  // 최대 유효 커넥션 객수

        return dataSource;
    }

    @Bean // 빈등록 하는 거임
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource());
    }

}
