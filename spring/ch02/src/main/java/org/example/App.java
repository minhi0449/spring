package org.example;

import org.example.config.AppConfig;
import org.example.sub1.Greeting;
import org.example.sub1.Hello;
import org.example.sub1.Welcome;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 날짜 : 2025/07/14
 * 이름 : 김민희
 * 내용 : ch02. Spring IoC/DI 실습하기
 */
public class App {
    public static void main(String[] args) {

        ///////////////////////////////////////////////////////////
        // 기존 객체 생성 방식
        Hello hello = new Hello();
        Welcome welcome = new Welcome();
        Greeting greeting = new Greeting();

        hello.show();
        welcome.show();
        greeting.show();

        ///////////////////////////////////////////////////////////
        // 스프링 컨테이너 객체 생성
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // 객체 주입 방식
        Hello helloBean = context.getBean(Hello.class);
        helloBean.show();

        Welcome welcomeBean = (Welcome) context.getBean("welcome");
        welcomeBean.show();

        Greeting greetingBean = (Greeting) context.getBean("ggg");
        greetingBean.show();

        ///////////////////////////////////////////////////////////




    }
}
