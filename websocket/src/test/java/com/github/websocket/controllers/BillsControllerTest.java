package com.github.websocket.controllers;

import com.github.websocket.dto.BillDto;
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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.github.websocket.WsConstant.LOCALHOST_HTTP;
import static com.github.websocket.WsConstant.LOCALHOST_WS;
import static com.github.websocket.controllers.BillsControllerMocks.STOMP_SESSION_ID;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class BillsControllerTest extends BillsControllerBase {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String billsUrl;

    @Before
    public void setUp() throws Exception {
        start(LOCALHOST_WS, port);
        String url = String.format(
                "%s%d%s", LOCALHOST_HTTP, port, "/v1/bills/"
        );
        this.billsUrl = new URL(url).toString();
    }

    @After
    public void tearDown() {
        stop();
    }

    @Test
    public void billNotify() throws InterruptedException, TimeoutException, ExecutionException {
        String topic = String.format("%s%s", "/topic/bills/", STOMP_SESSION_ID.toString());
        CompletableFuture<Object> billFuture = subscribe(topic, BillDto.class);
        BillDto payload = BillsControllerMocks.request();
        BillDto exp = BillsControllerMocks.request();
        String url = String.format("%s%s", this.billsUrl, STOMP_SESSION_ID.toString());
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.POST,
                new HttpEntity<>(payload),
                Void.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        BillDto act = (BillDto) billFuture.get(20, TimeUnit.SECONDS);
        assertEquals(exp, act);
    }

}
