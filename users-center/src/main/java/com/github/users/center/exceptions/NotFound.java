package com.github.users.center.exceptions;

public class NotFound extends RuntimeException {

    public NotFound() {
    }

    public NotFound(String message) {
        super(message);
    }
}
