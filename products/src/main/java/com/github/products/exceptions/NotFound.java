package com.github.products.exceptions;

import com.products.service.utils.TypeMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFound extends RuntimeException {
    public NotFound() {
    }

    public NotFound(String message) {
        super(message);
    }

    public NotFound(TypeMessage type) {
        super(TypeMessage.getMessage(type));
    }
}
