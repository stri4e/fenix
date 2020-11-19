package com.github.managers.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(value = "com.github.managers.entity")
@EnableJpaRepositories(basePackages = "com.github.managers.repository")
public class AppConfig {

}
