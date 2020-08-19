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
@Profile(value = "prod")
public class SecurityConfigProd {

    private static final String[] ALLOW_ACCESS = new String[] {
            "/users/**",
            "/products/**"
    };

    private static final String[] USER_ACCESS  = new String[] {
            "/products/v1/comments",
            "/orders/**"
    };

    private static final String[] MANAGER_ACCESS  = new String[] {
            "/managers/**"
    };

    private static final String[] ADMIN_ACCESS = new String[] {
            "/admin/**",
            "/users/v1/admins/reg",
            "/users/v1/managers/reg",
            "/statistics/**",
            "**/fetch/**",
            "**/edit/**",
            "/websocket/**"
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
                .pathMatchers(ADMIN_ACCESS).hasRole("ADMIN")
                .pathMatchers(MANAGER_ACCESS).hasRole("MANAGER")
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .anyExchange().authenticated()
                .and()
                .build();
    }

}
