package com.github.employees.exceptions;

public class PreconditionFailed extends RuntimeException {

    public PreconditionFailed() {
    }

    public PreconditionFailed(String message) {
        super(message);
    }
}
