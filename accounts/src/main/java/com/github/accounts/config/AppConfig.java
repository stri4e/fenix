package com.github.accounts.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(value = "com.github.accounts.entity")
@EnableJpaRepositories(basePackages = "com.github.accounts.repository")
public class AppConfig {

}
