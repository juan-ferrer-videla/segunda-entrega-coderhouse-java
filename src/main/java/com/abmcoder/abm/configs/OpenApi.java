package com.abmcoder.abm.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Commerce Api",
                version = "1.0",
                description = "E-commerce api coderhouse proyecto final"
        )
)
public class OpenApi {

}
