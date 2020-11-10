package com.github.ethereum.handlers;

import com.github.ethereum.payload.Error;
import com.github.facade.ethrereum.exceptions.BalanceException;
import com.github.facade.ethrereum.exceptions.BroadcastException;
import com.github.facade.ethrereum.exceptions.NonceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Error> handlerConflict(BalanceException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(new Error(
                status.value(), ex.getMessage()), status);
    }

    @ExceptionHandler
    public ResponseEntity<Error> handlerNotFound(BroadcastException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new Error(
                status.value(), ex.getMessage()), status);
    }

    @ExceptionHandler
    public ResponseEntity<Error> handlerBadRequest(NonceException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(new Error(
                status.value(), ex.getMessage()), status);
    }

}
