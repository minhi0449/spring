package com.ch03;


import org.aopalliance.aop.Advice;
import org.springframework.stereotype.Component;

@Component
public class MyService {



    // 여기는 핵심 관심
    public void insert(){
        System.out.println("핵심기능 - insert...");
    }
    public void select(String uid){
        System.out.println("핵심기능 - select...");

        if(uid.equals("a101")){
            System.out.println("핵심기능 - uid : " + uid);
        }


    }
    public void update(){
        System.out.println("핵심기능 - update...");
    }
    public void delete(){
        System.out.println("핵심기능 - delete...");
    }


}
