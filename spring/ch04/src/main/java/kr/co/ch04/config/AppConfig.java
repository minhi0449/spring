package kr.co.ch04.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/*
    스프링 MVC 설정을 담당하는 클래스
    이 클래스는 웹 애플리케이션의 전반적인 설정을 관리합니다.
 */
@Configuration // 스프링에게 '이 클래스는 설정 정보를 담고 있다'라고 알려줌
@ComponentScan(basePackages = {"kr.co.ch04"}) // "kr.co.ch04" 패키지와 하위 패키지에서 스프링 컴포넌트들을 자동으로 찾아서 등록해서
@EnableWebMvc // 스프링 웹 MVC 기능을 활성화 (웹 요청 처리, JSON 변환 등 기본 기능들이 자동 설정됨)
public class AppConfig implements WebMvcConfigurer {

    /*
        뷰 리졸버(View Resolver) 설정 메서드
        컨트롤러에서 리턴하는 뷰 이름을 실제 jsp 파일 경로로 변환하는 역할
        예: 컨트롤러에서 "user"를 리턴하면 --> "/WEB-INF/views/user.jsp" 파일을 찾아서 렌더링\

        ViewResolverRegistry : 웹페이지(JSP)를 찾는 방법을 알려줍니다.
        WebMvcConfigurer     : 위 기능들을 "내가 직접 설정하겠다"는 인터페이스

        [prefix: 접두어]
        JSP 파일이 저장된 폴더를 지정
        예: /WEB-INF/views/

        [suffix: 접미어]
        JSP 파일의 확정자를 지정
        예: .jsp

        [동작 흐름]
        만약 컨트롤러에서 return "login"; 이라고 하면
        --> "/WEB-INF/views/home.jsp" 파일을 찾아서 실행하게 됩니다.
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry){
        // ViewResolver 설정 (jsp 경로 위치 설정)
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        // ResourceHandler 설정 (정적 리소스 경로 설정)
        registry.addResourceHandler("/**").addResourceLocations("/resources/");
    }

}
