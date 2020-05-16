package com.github.admins.exceptions;

public enum TypeMessage {

    nullOrEmptyData("Null in method arguments."),
    dataNotFound("Data not found in db.");

    private String message;

    TypeMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
