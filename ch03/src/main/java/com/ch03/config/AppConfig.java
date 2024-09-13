package com.ch03.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.ch03"})
@EnableAspectJAutoProxy // 활성화 해줘야 Aspect기능을 한다
public class AppConfig {

}
