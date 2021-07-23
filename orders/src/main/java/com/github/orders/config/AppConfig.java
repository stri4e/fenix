package com.github.orders.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableCaching
@EnableJpaRepositories(basePackages = "com.github.orders.repository")
@EntityScan(basePackages = "com.github.orders.entity")
public class AppConfig {
}
