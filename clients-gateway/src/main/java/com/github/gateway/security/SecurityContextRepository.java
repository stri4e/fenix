package com.github.gateway.security;

import com.github.gateway.payload.JwtAuthResponse;
import com.github.gateway.utils.JsonHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SecurityContextRepository implements ServerSecurityContextRepository {

    public static final String TYPE_TOKEN = "Bearer";

    private final AuthenticationManager authenticationManager;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange swe) {
        ServerHttpRequest request = swe.getRequest();
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(authHeader)) {
            var jwtToken = JsonHelper.fromJson(authHeader, JwtAuthResponse.class);
            var token = Objects.nonNull(jwtToken) &&
                    TYPE_TOKEN.equals(jwtToken.getTokenType()) ? jwtToken.getAccessToken() : null;
            if (Objects.nonNull(token)) {
                Authentication auth = new UsernamePasswordAuthenticationToken(token, token);
                Mono<Authentication> result = this.authenticationManager.authenticate(auth);
                return result.map(SecurityContextImpl::new);
            }
        }
        return Mono.empty();
    }

}
