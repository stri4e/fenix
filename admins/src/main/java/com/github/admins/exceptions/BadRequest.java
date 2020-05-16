package com.github.admins.exceptions;

public class BadRequest extends RuntimeException {
    public BadRequest() {
    }

    public BadRequest(String message) {
        super(message);
    }

    public BadRequest(TypeMessage type) {
        super(type.getMessage());
    }

}
