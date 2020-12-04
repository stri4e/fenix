package com.github.websocket.controllers;

import com.github.websocket.dto.OrderDetailDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static com.github.websocket.WsConstant.LOCALHOST_HTTP;
import static com.github.websocket.WsConstant.LOCALHOST_WS;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class OrderControllerTest extends OrderControllerBase {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String ordersUrl;

    @Before
    public void setUp() throws Exception {
        start(LOCALHOST_WS, port);
        String url = String.format(
                "%s%d%s", LOCALHOST_HTTP, port, "/v1/push"
        );
        this.ordersUrl = new URL(url).toString();
    }

    @After
    public void tearDown() {
        stop();
    }

    @Test
    public void pushOrder() {
        OrderDetailDto payload = OrderControllerMocks.request();
        ResponseEntity<Void> response = this.restTemplate.exchange(
                this.ordersUrl, HttpMethod.POST,
                new HttpEntity<>(payload),
                Void.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

}
