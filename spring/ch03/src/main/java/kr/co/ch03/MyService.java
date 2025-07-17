package kr.co.ch03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyService {
    @Autowired
    private MyAdvice myAdvice;

    public void insert(){
        System.out.println("[MyService] 핵심기능 - insert()");
    }

    public void select(String id){
        System.out.println("[MyService] 핵심기능 - select()");

        if(id.equals("a101")){
            System.out.println("[MyService] 핵심기능 - select(id) : " + id);
        }
    }

    public void update(){
        System.out.println("[MyService] 핵심기능 - update()");
    }

    public void delete(){
        System.out.println("[MyService] 핵심기능 - delete()");
    }
}
