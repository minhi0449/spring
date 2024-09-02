package com.ch02.sub2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("com")
public class Computer {
    
    // 필드 주입 (직접 주입하는 것이떄문에 별로 안좋음)
    @Autowired
    private CPU cpu;

    // 생성자 주입 (권장)
    private RAM ram;

    @Autowired
    public Computer(RAM ram) {
        this.ram = ram;
    }


    // 세터 주입 (setter)
    private SSD ssd;

    // 주입하는 어노테이션
    @Autowired
    public void setSsd(SSD ssd) {
        this.ssd = ssd;
    }

    public void show(){
        cpu.show();
        ram.show();
        ssd.show();

    }
}
