package com.github.master.card.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT)
public class NoContent extends RuntimeException {

    public NoContent() {
    }

    public NoContent(String message) {
        super(message);
    }
}
