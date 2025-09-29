package com.example.wordy.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;


@OpenAPIDefinition(
        info = @Info(
                title = "Loyalty System Api",
                description = "API системы лояльности",
                version = "1.0.0",
                contact = @Contact(
                        name = "Ivan Timerbulatov",
                        email = "isip@mpt.ru",
                        url = "https://wordy.ru"
                )
        )
)
public class OpenApiConfig {
}


