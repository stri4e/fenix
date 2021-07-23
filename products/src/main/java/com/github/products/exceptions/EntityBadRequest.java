package com.github.products.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Entity can't be null!")
public class EntityBadRequest extends RuntimeException {

    public EntityBadRequest() {
    }

}
