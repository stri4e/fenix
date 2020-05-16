package com.github.admins.exceptions;

public class NotFound extends RuntimeException {

    public NotFound() {
    }

    public NotFound(String message) {
        super(message);
    }

    public NotFound(TypeMessage type) {
        super(type.getMessage());
    }
}
