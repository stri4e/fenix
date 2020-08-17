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

    private final AuthenticationManager authenticationManager;

    private final SecurityContextRepository securityContextRepository;

    @Bean
    public SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
        String[] allowAccess = new String[] {
                "/users/**", "/products/**"
        };
        String[] userAccess  = new String[] {
                "/products/v1/comments", "/orders/**"
        };
        String[] adminAccess = new String[] {
                "/admin/**", "/users/v1/admins/reg", "/statistics/**", "**/fetch/**", "**/edit/**", "/websocket/**"
        };
        String [] managerAccess = new String[] {
                "/orders/v1/handling", "/websocket/**"
        };
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
                .pathMatchers(allowAccess).permitAll()
                .pathMatchers(userAccess).hasRole("USER")
                .pathMatchers(adminAccess).hasRole("ADMIN")
                .pathMatchers(managerAccess).hasRole("ROLE_MANAGER")
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .anyExchange().authenticated()
                .and()
                .build();
    }

}
