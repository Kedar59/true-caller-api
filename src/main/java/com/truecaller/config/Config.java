package com.truecaller.config;


import com.truecaller.entities.Profile;
import com.truecaller.services.ProfileService;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Config {
    @Bean
    public OpenAPI myOpenAPI() {
        return new OpenAPI();
    }
}

