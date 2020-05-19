package com.github.admins.handlers;

import com.github.admins.exceptions.BadRequest;
import com.github.admins.exceptions.NotFound;
import com.github.admins.payload.Error;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Error> handlerNotFound(NotFound ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(new Error(
                status.value(), ex.getMessage()), status);
    }

    @ExceptionHandler
    public ResponseEntity<Error> handlerNotFound(BadRequest ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new Error(
                status.value(), ex.getMessage()), status);
    }

    @ExceptionHandler
    public ResponseEntity<Error>
    handleFeignStatusException(FeignException e, HttpServletResponse response) {
        response.setStatus(e.status());
        return new ResponseEntity<>(new Error(e.status(), e.getMessage()),
                HttpStatus.valueOf(e.status()));
    }

}
