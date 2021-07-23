package com.github.products.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity not found in database!")
public class EntityNotFound extends RuntimeException {

    public EntityNotFound() {
    }
}
