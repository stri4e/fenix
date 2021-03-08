package com.github.config.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FAILED_DEPENDENCY, reason = "Can't serialize json.")
public class FailedDependencyException extends RuntimeException {

    public FailedDependencyException() {
    }

    public FailedDependencyException(String message) {
        super(message);
    }

}
