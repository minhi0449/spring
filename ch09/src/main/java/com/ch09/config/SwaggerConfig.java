package com.ch09.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    // http://localhost:8080/ch09/swagger-ui/index.html

    @Bean
    public OpenAPI OpenAPI() {

        Info info = new Info();
        info.title("ch09장 Swagger API 문서");
        info.version("1.0.0");
        info.description("ch09에서 제공하는 API 메뉴얼입니다.");



        return new OpenAPI()
                        .components(new Components())
                        .info(info);
    }


}
