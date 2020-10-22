package com.github.websocket.network;

import com.github.websocket.utils.Logging;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class Broker {

    private final ConnectionPool pool;

    private final SimpMessageSendingOperations sendingOperations;

    @Logging
    public void sendOrder(Object payload) {
        var sessionId = this.pool.getSession();
        SimpMessageHeaderAccessor headerAccessor =
                SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        this.sendingOperations.convertAndSendToUser(
                sessionId, "/topic/orders",
                payload, headerAccessor.getMessageHeaders()
        );
    }

    @Logging
    public void broadcast(Object payload) {
        this.sendingOperations.convertAndSend("/topic/common", payload);
    }

    @Logging
    public void sendBill(String ending, Object payload) {
        this.sendingOperations.convertAndSend(
                format("%s%s", "/topic/common/", ending),
                payload
        );
    }

}
