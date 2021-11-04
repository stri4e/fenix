package com.github.employees.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Mono;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Configuration
@EnableReactiveMongoAuditing(auditorAwareRef = "auditProvider")
@EnableReactiveMongoRepositories(basePackages = "com.github.employees.repository")
public class AppConfig {

    private static final String USER_ID = "userId";

    @Bean
    public WebFilter webFilter() {
        return (exchange, chain) -> {
            ServerHttpRequest req = exchange.getRequest();
            HttpHeaders headers = req.getHeaders();
            List<String> values = headers.getOrEmpty(HttpHeaders.AUTHORIZATION);
            return chain.filter(exchange).contextWrite(ctx -> ctx.put(USER_ID, values.stream().findFirst().orElse("")));
        };
    }

    @Bean
    public ReactiveAuditorAware<UUID> auditProvider() {
        return () -> Mono.deferContextual(context -> Mono.just(context.get(USER_ID)));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
