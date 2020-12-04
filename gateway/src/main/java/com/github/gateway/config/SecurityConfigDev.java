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
            "/products/v1/many/bought/count/**",
            "/products/v1/single/bought/count/**",
            "/orders/**",
            "/statistics/v1/unbooked",
            "/users/v1/aliases/listening",
            "/payments/v1/accountant",
            "/payments/v1/assets",
            "/payments/v1/bills/**",
            "/payments/v1/rates/**",
            "/payments/v1/payment/type",
            "/accounts/**",
            "/geolocation/**",
            "/customers/**",
            "/deliveries/**",
            "/bitcoin/v1/accounts/**",
            "/bitcoin/v1/currency/**",
            "/ethereum/v1/accounts/**",
            "/ethereum/v1/contracts/**",
            "/ethereum/v1/currency/**",
            "/ethereum/v1/fee/**",
            "/master-card/v1/account/**",
            "/master-card/v1/payment/**"
    };

    private static final String[] MANAGER_ACCESS  = new String[] {
            "/managers/**",
            "/statistics/**",
            "/users/v1/aliases/listening",
            "/payments/**",
            "/geolocation/**",
            "/customers/**",
            "/deliveries/**"
    };

    private static final String[] ADMIN_ACCESS = new String[] {
            "/admin/**",
            "/users/v1/admins/reg",
            "/users/v1/managers/reg",
            "/statistics/**",
            "/users/v1/aliases/listening",
            "/ethereum/**",
            "/bitcoin/**",
            "/payments/**",
            "/geolocation/**",
            "/customers/**",
            "/deliveries/**",
            "/master-card/**"
    };

    private static final String [] SERVICE_ACCESS = new String[] {
            "**/fetch/**",
            "**/edit/**",
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
                .pathMatchers(SERVICE_ACCESS).hasRole("ROLE_SERVICE")
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .anyExchange().authenticated()
                .and()
                .build();
    }

}
