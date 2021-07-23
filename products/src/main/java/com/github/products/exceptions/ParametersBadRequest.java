package com.github.products.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Parameter or parameters can't be null or empty!")
public class ParametersBadRequest extends RuntimeException {
}
