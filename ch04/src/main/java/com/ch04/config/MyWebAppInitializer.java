package com.ch04.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

// Initializer = 초기화 프로그램
// MyWebAppInitializer = 나의 웹앱 초기화 프로그램
public class MyWebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 스프링 MVC 애플리케이션 컨텍스트 생성
        // 컨텍스트 : 스프링 컨테이너 라고 생각하기
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

        // 스프링 MVC 구성 클래스 등록
        context.register(AppConfig.class);

        // DispatcherServlet 생성/등록 (핵심)
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic ServletRegistration
                = servletContext.addServlet("dispatcherServlet", dispatcherServlet);

        ServletRegistration.setLoadOnStartup(1);
        ServletRegistration.addMapping("/");

    }
}
