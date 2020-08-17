package com.github.orders.controllers.impl;

import com.github.orders.dto.OrderDetailDto;
import com.github.orders.entity.Customer;
import com.github.orders.entity.OrderDetail;
import com.github.orders.repository.CustomerRepo;
import com.github.orders.repository.OrderDetailRepo;
import org.assertj.core.util.Lists;
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

import static com.github.orders.OrdersConstant.LOCALHOST;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class OrdersDetailControllerTest extends OrdersDetailTestBase {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OrderDetailRepo orderRepo;

    @Autowired
    private CustomerRepo customerRepo;

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
                "%s%d%s", LOCALHOST, port, "/v1"
        );
        this.orderUrl = new URL(url).toString();
    }

    @Test
    public void save() {
        saveOrder();
        OrderDetailDto payload = OrdersDetailControllerMocks.payload();
        ResponseEntity<Void> response = this.restTemplate.exchange(
                this.orderUrl, HttpMethod.POST, new HttpEntity<>(payload), Void.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void findAllByStatus() {
        List<OrderDetail> exp = Lists.newArrayList(OrdersDetailControllerMocks.expOrder());
        OrderDetail order = OrdersDetailControllerMocks.orderDetail();
        Customer customer = OrdersDetailControllerMocks.customer();
        this.customerRepo.save(customer);
        this.orderRepo.save(order);
        String url = String.format("%s%s", this.orderUrl, "/fetch/open");
        ResponseEntity<List<OrderDetail>> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<OrderDetail> act = response.getBody();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

    @Test
    public void findByOrderId() {
        OrderDetail exp = OrdersDetailControllerMocks.expOrder();
        OrderDetail order = OrdersDetailControllerMocks.orderDetail();
        Customer customer = OrdersDetailControllerMocks.customer();
        this.customerRepo.save(customer);
        this.orderRepo.save(order);
        String url = String.format("%s%s", this.orderUrl, "/fetch?orderId=1");
        ResponseEntity<OrderDetail> response = this.restTemplate
                .getForEntity(url, OrderDetail.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        OrderDetail act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void update() {
        OrderDetail order = OrdersDetailControllerMocks.orderDetailForUpdate();
        Customer customer = OrdersDetailControllerMocks.customer();
        this.customerRepo.save(customer);
        this.orderRepo.save(order);
        String url = String.format("%s%s", this.orderUrl, "/edit");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.PUT, new HttpEntity<>(order), Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateOrderStatus() {
        OrderDetail order = OrdersDetailControllerMocks.orderDetail();
        Customer customer = OrdersDetailControllerMocks.customer();
        this.customerRepo.save(customer);
        this.orderRepo.save(order);
        String url = String.format("%s%s", this.orderUrl, "/edit/1/close");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.PUT, new HttpEntity<>(order), Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
