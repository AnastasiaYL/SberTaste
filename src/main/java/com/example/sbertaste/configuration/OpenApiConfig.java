package com.example.sbertaste.configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer")
public class OpenApiConfig {
    @Bean
    public OpenAPI pizzaProject() {
        return new OpenAPI()
                .info(new Info()
                        .description("Order pizza online!")
                        .title("SberTaste")
                        .version("v1.0")
                        .contact(new Contact().name("Anastasia L. & Alexey K."))
                );
    }
}
