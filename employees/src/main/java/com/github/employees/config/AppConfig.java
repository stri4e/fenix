package com.github.employees.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.employees.models.AccessKey;
import com.github.employees.models.KeysStore;
import com.github.employees.models.RefreshKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
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

    @Bean
    public Map<String, KeysStore>
    keysStore(@Value(value = "#{${keys.main.store}}") Object keysMainStore, ObjectMapper mapper) {
        return mapper.convertValue(keysMainStore, new TypeReference<>() {});
    }

    @Bean
    public Map<String, AccessKey> accessKeyStore(Map<String, KeysStore> keysStore) {
        return keysStore.values().stream()
                .map(KeysStore::getAccessKey)
                .collect(Collectors.toMap(AccessKey::getId, Function.identity()));
    }

    @Bean
    public Map<String, RefreshKey> refreshKeyStore(Map<String, KeysStore> keysStore) {
        return keysStore.values().stream()
                .map(KeysStore::getRefreshKey)
                .collect(Collectors.toMap(RefreshKey::getId, Function.identity()));
    }

}
