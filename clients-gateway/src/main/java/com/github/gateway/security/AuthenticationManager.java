package com.github.gateway.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtTokenProvider tokenProvider;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        var token = authentication.getCredentials().toString();
        var key = this.tokenProvider.findKey(token);
        var userId = this.tokenProvider.getUserFromJwt(token, key);
        if (Objects.nonNull(userId) && this.tokenProvider.validateToken(token)) {
            List<String> roles = this.tokenProvider.getRoles(token, key);
            List<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(userId, userId, authorities);
            SecurityContextHolder.getContext().setAuthentication(auth);
            return Mono.just(auth);
        }
        return Mono.empty();
    }
}
