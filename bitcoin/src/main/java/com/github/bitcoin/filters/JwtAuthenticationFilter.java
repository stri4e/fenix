package com.github.bitcoin.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.bitcoin.utils.JwtTokenProvider;
import com.google.common.net.HttpHeaders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements Filter {

    public static final String TYPE_HTTP_TOKEN = "Bearer";

    private final JwtTokenProvider tokenProvider;

    private final ObjectMapper mapper;

    @Override
    public void doFilter(ServletRequest request,
            ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        var token = getJwtFromRequest(req);
        if (StringUtils.hasText(token)) {
            var userId = this.tokenProvider.getUserFromJwt(token);
            req.setAttribute("userId", userId);
        }
        chain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        var bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken)) {
            try {
                ObjectNode data = this.mapper.readValue(bearerToken, ObjectNode.class);
                if (data != null && data.get("tokenType").asText().equals(TYPE_HTTP_TOKEN)) {
                    return data.get("accessToken").asText();
                }
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

}
