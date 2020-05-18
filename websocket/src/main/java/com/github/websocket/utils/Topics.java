package com.github.websocket.utils;

public enum Topics {

    orders("/topic/orders");

    private String url;

    Topics(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}
