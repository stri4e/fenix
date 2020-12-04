package com.github.bitcoin.exceptions;

public class SendTransactionFailed extends RuntimeException {

    public SendTransactionFailed(String message) {
        super(message);
    }
}
