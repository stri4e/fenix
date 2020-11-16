package com.github.master.card.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableCaching
@EntityScan(value = "com.github.master.card.entity")
@EnableJpaRepositories(basePackages = "com.github.master.card.repository")
public class AppConfig {
}
