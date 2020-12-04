package com.github.websocket.utils;

public enum Topics {

    orders("/topic/orders"),

    bills("/topic/bills/"),

    commons("/topic/common");

    private final String url;

    Topics(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}
