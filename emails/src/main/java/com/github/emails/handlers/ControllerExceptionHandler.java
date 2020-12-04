package com.github.emails.handlers;

import feign.FeignException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public String feignError(FeignException e, HttpServletResponse response) {
        return "base-error";
    }

}
