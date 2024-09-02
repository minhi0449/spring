package com.ch02;

import com.ch02.config.AppConfig;
import com.ch02.sub1.Greeting;
import com.ch02.sub1.Hello;
import com.ch02.sub1.Welcome;
import com.ch02.sub2.Computer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 날짜 : 2024.09.02 (월)
 * 이름 : 김민희
 * 내용 ; 2장 Spring Ioc/DI 실습하기
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        // 스프링 컨테이너 생성
        // 의존성 정보가 필요함
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // 빈 가져오기
        Hello hello = (Hello) context.getBean(Hello.class);
        hello.show();

        Welcome welcome = (Welcome) context.getBean("welcome");
        welcome.show();

        Greeting greeting = (Greeting) context.getBean("greet");
        greeting.show();


        // Ioc/Di 실습
        Computer com = (Computer) context.getBean("com");
        com.show();

    }
}






































