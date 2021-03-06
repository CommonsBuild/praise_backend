package com.praisesystem.backend.configuration.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI api() {
        OpenAPI openAPI = new OpenAPI()
                .info(new Info().title("Praise System Backend"))
                .components(new Components().addResponses("400", new ApiResponse().description("BAD REQUEST")));
        // TODO: 05.10.2021 create Pageable substitution
        return openAPI;
    }
}
