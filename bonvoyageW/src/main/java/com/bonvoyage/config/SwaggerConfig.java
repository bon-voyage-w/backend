package com.bonvoyage.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info().title("BonVoyage")
                .description("BonVoyage API Reference for Developer")
                .termsOfService("http://swagger.io/terms/")
                .license(new License()
                        .name("Apache License Version 2.0")
                        .url("http://www.apache.org/licenses/LICENSE-2.0"));

        return new OpenAPI()
                .info(info);
    }

    @Bean//domain별로 작성하기!
    public GroupedOpenApi user(){
        return GroupedOpenApi.builder()
                .group("user")
                .pathsToMatch("/users/**")
                .packagesToScan("com.bonvoyage.domain")
                .build();
    }
}
