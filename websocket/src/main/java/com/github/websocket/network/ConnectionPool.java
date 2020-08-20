package com.github.websocket.network;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class ConnectionPool {

    private final Queue<String> session = new ConcurrentLinkedQueue<>();

    public void add(String session) {
        this.session.offer(session);
    }

    public void remove(String session) {
        this.session.remove(session);
    }

    public String getSession() {
        String elem = this.session.poll();
        if (StringUtils.hasText(elem)) {
            this.session.offer(elem);
        }
        return elem;
    }

}
