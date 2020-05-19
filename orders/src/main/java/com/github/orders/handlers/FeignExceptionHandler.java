package com.github.orders.handlers;

import com.github.orders.payload.Error;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
public class FeignExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Error>
    handleFeignStatusException(FeignException e, HttpServletResponse response) {
        response.setStatus(e.status());
        log.error("Response status {},  message: {}", e.status(), e.getMessage());
        return new ResponseEntity<>(new Error(e.status(), e.getMessage()),
                HttpStatus.valueOf(e.status()));
    }

}
