package com.github.emails.utils;

public enum  EmailConstants {

    gatewayService("gateway"),

    firstName,

    lastName,

    confirmAccountUrl,

    signature;

    private String info;

    EmailConstants() {
    }

    EmailConstants(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

}
