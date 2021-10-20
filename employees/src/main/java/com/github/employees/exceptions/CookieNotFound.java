package com.github.employees.exceptions;

public class CookieNotFound extends RuntimeException {
    public CookieNotFound() {
        super("Required cookie not found in this scope.");
    }
}
