package com.github.statistics.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(value = "com.github.statistics.entity")
@EnableJpaRepositories(basePackages = "com.github.statistics.repository")
public class AppConfig {
}
