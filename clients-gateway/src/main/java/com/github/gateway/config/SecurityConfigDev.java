package com.github.gateway.config;

import com.github.gateway.security.AuthenticationManager;
import com.github.gateway.security.SecurityContextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
@Profile(value = "dev")
public class SecurityConfigDev {

    private static final String[] ALLOW_ACCESS = new String[] {
            "/users/**",
            "/products/**",
            "/emails/pages/**"
    };

    private static final String[] USER_ACCESS  = new String[] {
            "/products/v1/comments",
            "/orders/**",
            "/customers/**",
            "/geolocation/**"
    };

    private static final String [] DENY_ALL = new String[] {
            "**/fetch/**",
            "**/edit/**",
            "**/employees/**"
    };

    private final AuthenticationManager authenticationManager;

    private final SecurityContextRepository securityContextRepository;

    @Bean
    public SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
        return http.cors().disable()
                .exceptionHandling()
                .authenticationEntryPoint((swe, e) -> Mono.fromRunnable(() -> {
                    swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                })).accessDeniedHandler((swe, e) -> Mono.fromRunnable(() -> {
                    swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                })).and()
                .csrf().disable()
                .authenticationManager(this.authenticationManager)
                .securityContextRepository(this.securityContextRepository)
                .authorizeExchange()
                .pathMatchers(ALLOW_ACCESS).permitAll()
                .pathMatchers(USER_ACCESS).hasRole("USER")
                .pathMatchers(DENY_ALL).denyAll()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .anyExchange().authenticated()
                .and()
                .build();
    }

}
