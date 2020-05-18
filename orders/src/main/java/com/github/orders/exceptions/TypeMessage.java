package com.github.orders.exceptions;

public enum TypeMessage {

    badOrderData("Bad data order."),

    orderFindError("Order not found.");

    private String message;

    TypeMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
