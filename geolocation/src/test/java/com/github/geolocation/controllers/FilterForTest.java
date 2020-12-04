package com.github.geolocation.controllers;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

@Component
public class FilterForTest implements Filter {

    @Override
    public void
    doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        request.setAttribute("ip", "1.179.128.0");
        chain.doFilter(request, response);
    }

}
