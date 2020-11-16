package com.github.admins.controllers.impl;

import com.github.admins.dto.OrderDetailDto;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.integration.ClientAndServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.util.List;

import static com.github.admins.AdminsConstant.LOCALHOST;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class OrderDetailControllerTest extends OrderDetailTestBase {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String orderUrl;

    private static ClientAndServer mockServer;

    @BeforeClass
    public static void startServer() {
        mockServer = ClientAndServer.startClientAndServer(2222);
    }

    @AfterClass
    public static void downServer() {
        mockServer.stop();
    }

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s", LOCALHOST, port, "/v1/orders"
        );
        this.orderUrl = new URL(url).toString();
    }

    @Test
    public void findByStatusOpen() {
        statusOpen();
        String url = String.format("%s%s", this.orderUrl, "/all/open");
        List<OrderDetailDto> exp = OrderDetailControllerMocks.ORDERS_DTO_OPEN_DTO;
        ResponseEntity<List<OrderDetailDto>> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<OrderDetailDto> act = response.getBody();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

    @Test
    public void findByStatusClose() {
        statusClose();
        String url = String.format("%s%s", this.orderUrl, "/all/close");
        List<OrderDetailDto> exp = OrderDetailControllerMocks.ORDERS_CLOSE_DTO;
        ResponseEntity<List<OrderDetailDto>> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<OrderDetailDto> act = response.getBody();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

    @Test
    public void findByStatusHandling() {
        statusHandling();
        String url = String.format("%s%s", this.orderUrl, "/all/handling");
        List<OrderDetailDto> exp = OrderDetailControllerMocks.ORDERS_DTO_HANDLING;
        ResponseEntity<List<OrderDetailDto>> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<OrderDetailDto> act = response.getBody();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

    @Test
    public void findById() {
        findOrderById();
        OrderDetailDto exp = OrderDetailControllerMocks.orderDetailDto();
        String url = String.format("%s%s", this.orderUrl, "/2");
        ResponseEntity<OrderDetailDto> response = this.restTemplate
                .getForEntity(url, OrderDetailDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        OrderDetailDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void updateOrderStatus() {
        updateStatus();
        String url = String.format("%s%s", this.orderUrl, "/status/1/open");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.PUT, null, Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
