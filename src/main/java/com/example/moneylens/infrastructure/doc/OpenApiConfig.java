package com.example.moneylens.infrastructure.doc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String BEARER = "bearerAuth";

    @Bean
    public OpenAPI moneylensOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("MoneyLens API")
                        .version("v1"))
                .components(new Components()
                        .addSecuritySchemes(BEARER, new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Dán access token (không cần chữ 'Bearer ')")))
                // Áp dụng JWT cho mọi API
                .addSecurityItem(new SecurityRequirement().addList(BEARER));
    }
}
