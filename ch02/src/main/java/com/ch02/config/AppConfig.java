package com.ch02.config;

import com.ch02.sub1.Greeting;
import com.ch02.sub1.Hello;
import com.ch02.sub1.Welcome;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// 컨테이너가 관리하는 (의존하는) config
@Configuration
@ComponentScan(basePackages = {"com.ch02"})
public class AppConfig {

    @Bean
    public Hello hello (){
        return new Hello();
    }

    // welcome 객체의 이름이 welcome
    @Bean(name = "welcome")
    public Welcome welcome(){
        return new Welcome();
    }

    @Bean("greet")
    public Greeting greeting(){
        return new Greeting();
    }




}
