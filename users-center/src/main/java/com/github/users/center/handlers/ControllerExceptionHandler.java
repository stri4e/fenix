package com.github.users.center.handlers;

import com.github.users.center.exceptions.*;
import com.github.users.center.payload.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Error> handlerConflict(Conflict ex) {
        HttpStatus status = HttpStatus.CONFLICT;
        return new ResponseEntity<>(new Error(
                status.value(), ex.getMessage()), status);
    }

    @ExceptionHandler
    public ResponseEntity<Error> handlerNotFound(NotFound ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(new Error(
                status.value(), ex.getMessage()), status);
    }

    @ExceptionHandler
    public ResponseEntity<Error> handlerBadRequest(BadRequest ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new Error(
                status.value(), ex.getMessage()), status);
    }

    @ExceptionHandler
    public ResponseEntity<Error> handlerUnauthorized(Unauthorized ex) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        return new ResponseEntity<>(new Error(
                status.value(), ex.getMessage()), status);
    }

    @ExceptionHandler
    public ResponseEntity<Error> handlerPreconditionFailed(PreconditionFailed ex) {
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        return new ResponseEntity<>(new Error(
                status.value(), ex.getMessage()), status);
    }

}
