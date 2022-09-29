package com.ay.exchange.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi userApi(){
        return GroupedOpenApi.builder()
                .group("user-api")
                .pathsToMatch("/user/**")
                .build();
    }

    @Bean
    public GroupedOpenApi boardApi(){
        return GroupedOpenApi.builder()
                .group("board-api")
                .pathsToMatch("/board/**")
                .build();
    }

    @Bean
    public GroupedOpenApi commentApi(){
        return GroupedOpenApi.builder()
                .group("comment-api")
                .pathsToMatch("/comment/**")
                .build();
    }

    @Bean
    public OpenAPI openApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("AYU Campus API")
                        .description("API 명세서")
                        .version("0.0.1"));
    }
}
