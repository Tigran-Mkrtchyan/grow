package com.grow.common_ui.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Accessors(fluent = true)
public class ApplicationConfig {

    @Getter
    @Value("${opp.security.apiKey}")
    private String apiKey;
}