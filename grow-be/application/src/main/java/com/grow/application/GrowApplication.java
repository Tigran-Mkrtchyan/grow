package com.grow.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.grow")
@EntityScan(basePackages = "com.grow")
@EnableJpaRepositories(basePackages = "com.grow")
public class GrowApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrowApplication.class, args);
    }
}
