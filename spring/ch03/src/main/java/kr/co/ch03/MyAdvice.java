package kr.co.ch03;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAdvice {

    @Pointcut("execution(void kr.co.ch03.MyService.insert())")
    public void insertPointcut(){}

    @Pointcut("execution(void kr.co.ch03.MyService.select*(..))")
    public void selectPointcut(){}

    @Before("insertPointcut() || selectPointcut()")
    public void beforeAdvice(){
        System.out.println("[MyAdvice] 부가기능 - beforeAdvice()");
    }

    @After("insertPointcut() || selectPointcut()")
    public void afterAdvice(){
        System.out.println("[MyAdvice] 부가기능 - afterAdvice()");
    }

    @AfterReturning("insertPointcut()")
    public void afterReturnAdvice(){
        System.out.println("[MyAdvice] 부가기능 - afterReturnAdvice()");
    }

    @Around("insertPointcut()")
    public void aroundAdvice(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("[MyAdvice] 부가기능 - aroundAdvice(1)");
        pjp.proceed(); // 핵심기능 실행
        System.out.println("[MyAdvice] 부가기능 - aroundAdvice(2)");
    }

    @AfterThrowing("selectPointcut()")
    public void afterThrowAdvice(){
        System.out.println("[MyAdvice] 부가기능 - afterThrowAdvice()");
    }
}
