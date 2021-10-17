package com.github.employees.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Configurable
@EnableReactiveMongoAuditing(auditorAwareRef = "auditProvider")
@EnableReactiveMongoRepositories(basePackages = "com.github.employees.repository")
public class AppConfig {

    // TODO: 15.09.21 add some of filter for get this
    @Bean
    public ReactiveAuditorAware<UUID> auditProvider() {
        return () -> Mono.deferContextual(context -> context.get("userId"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
