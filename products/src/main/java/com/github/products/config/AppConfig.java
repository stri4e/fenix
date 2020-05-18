package com.github.products.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableCaching
@EntityScan(value = "com.github.products.entity")
@EnableJpaRepositories(basePackages = "com.github.products.repository")
public class AppConfig {
}
