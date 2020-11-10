package com.github.ethereum.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Max limit of account")
public class Conflict extends RuntimeException {

    public Conflict() {
    }

    public Conflict(String message) {
        super(message);
    }

}
