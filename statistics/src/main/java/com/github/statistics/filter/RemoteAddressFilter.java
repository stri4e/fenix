package com.github.statistics.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

@Component
@Order(value = 0)
public class RemoteAddressFilter implements Filter {

    private static final String[] IP_HEADER_CANDIDATES = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest r = (HttpServletRequest) request;
        String result = Arrays.stream(IP_HEADER_CANDIDATES)
                .map(r::getHeader)
                .filter(ip -> ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip))
                .findFirst().orElse(request.getRemoteAddr());
        request.setAttribute("ip", result);
        chain.doFilter(request, response);
    }

}
