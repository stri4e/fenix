package com.github.customers.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(value = "com.github.customers.entity")
@EnableJpaRepositories(basePackages = "com.github.customers.repository")
public class AppConfig {

}
