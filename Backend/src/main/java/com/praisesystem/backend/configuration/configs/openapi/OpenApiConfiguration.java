package com.praisesystem.backend.configuration.configs.openapi;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "jwt",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfiguration {

    @Bean
    public OpenAPI api() {
        OpenAPI openAPI = new OpenAPI()
                .info(new Info().title("Praise System Backend"))
                .components(new Components().addResponses("400", new ApiResponse().description("BAD REQUEST")));
        return openAPI;
    }
}
