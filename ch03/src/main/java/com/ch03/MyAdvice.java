package com.ch03;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAdvice {

    // 포인트 컷
    @Pointcut("execution(void com.ch03.MyService.insert())")
    public void insertPoincut(){} // 핵심관심을 가리키는 내용이 없는 메서드 (인서트를 가리키는 포인트 컷)

    @Pointcut("execution(void com.ch03.MyService.select(..))")
    public void selectPointCut(){}



    // insertPoincut() 실행될 때 selectPointCut() 이것도 실행됨
    @Before("insertPoincut() || selectPointCut()")
    public void beforeAdvice() {
        System.out.println("부가기능 - beforeAdvice...");
    }

    @After("insertPoincut()")
    public void afterAdvice() {
        System.out.println("부가기능 - afterAdvice...");
    }

    @AfterReturning("insertPoincut()")
    public void afterReturningAdvice() {
        System.out.println("부가기능 - afterReturningAdvice...");
    }


    @Around("insertPoincut()")
    public void aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("부가기능 - aroundAdvice...1");
        pjp.proceed(); // 핵심관심
        System.out.println("부가기능 - aroundAdvice...2");
    }

    @AfterThrowing("selectPointCut()")
    public void afterThrowingAdvice() {
        System.out.println("부가기능 - afterThrowingAdvice...");
    }
}


















