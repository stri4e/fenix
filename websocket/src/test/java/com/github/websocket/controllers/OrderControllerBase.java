package com.github.websocket.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

public class OrderControllerBase {


    private static final Logger log = LoggerFactory.getLogger(BillsControllerBase.class);

    protected AtomicReference<Object> storage = new AtomicReference<>();

    private WebSocketHttpHeaders headers = new WebSocketHttpHeaders();

    private ListenableFuture<StompSession> connect;

    protected StompSession session;

    protected String sessionId;

    public void start(String host, Integer port) throws ExecutionException, InterruptedException {
        this.connect = connect(host, port);
        this.session = this.connect.get();
    }

    public void stop() {
        this.connect.cancel(Boolean.TRUE);
    }

    public ListenableFuture<StompSession> connect(String host, Integer port) {
        List<Transport> transports = new ArrayList<>();
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        container.setDefaultMaxBinaryMessageBufferSize(20024);
        container.setDefaultMaxTextMessageBufferSize(20024);
        transports.add(new WebSocketTransport(new StandardWebSocketClient(container)));
        WebSocketClient webSocketClient = new SockJsClient(transports);
        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setInboundMessageSizeLimit(Integer.MAX_VALUE);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        String url = "ws://{host}:{port}/events";
        return stompClient.connect(url, this.headers, new WsClientHandler(), host, port);
    }

    private class WsClientHandler extends StompSessionHandlerAdapter {

        @Override
        public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
            log.info("Enter: {}", session.getSessionId());
            sessionId = session.getSessionId();
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            log.info("Enter: {}", payload);
        }

    }

    public CompletableFuture<Object> subscribe(String topic, Class<?> clazz) {
        return CompletableFuture.supplyAsync(() -> {
            this.session.subscribe(topic, new StompFrameHandler() {
                @Override
                public Type getPayloadType(StompHeaders headers) {
                    return clazz;
                }

                @Override
                public void handleFrame(StompHeaders headers, Object payload) {
                    storage.set(payload);
                }
            });
            Object result;
            while (Objects.isNull(result = this.storage.get())) {
            }
            return result;
        });
    }

}
