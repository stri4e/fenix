package com.github.ethereum.controllers;

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
        request.setAttribute("userId", UUID.fromString("9da421d7-33e4-4319-9288-0f503bd20c3d"));
        chain.doFilter(request, response);
    }

}
