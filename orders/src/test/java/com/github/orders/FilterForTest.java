package com.github.orders;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class FilterForTest implements Filter {

    @Override
    public void
    doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        request.setAttribute("userId", 1L);
        chain.doFilter(request, response);
    }

}
