package com.github.users.center.exceptions;

public class Conflict extends RuntimeException {
    public Conflict() {
    }

    public Conflict(String message) {
        super(message);
    }
}
