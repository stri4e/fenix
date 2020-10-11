package com.github.bitcoin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public final class Error implements Serializable, Cloneable {

    private static final long serialVersionUID = -7529241338595984194L;

    private final int status;

    private final String message;

}
