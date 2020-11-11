package com.github.orders.controllers.impl;

import com.github.orders.dto.OrderDetailDto;
import com.github.orders.entity.Customer;
import com.github.orders.entity.Delivery;
import com.github.orders.entity.OrderDetail;
import com.github.orders.repository.CustomerRepo;
import com.github.orders.repository.DeliveryRepo;
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
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
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

    @Autowired
    private DeliveryRepo deliveryRepo;

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
        readProductsByIds();
        readBillById();
        List<OrderDetailDto> exp = Lists.newArrayList(OrdersDetailControllerMocks.orderForExpected());
        OrderDetail order = OrdersDetailControllerMocks.orderDetail();
        Customer customer = OrdersDetailControllerMocks.customer();
        Delivery delivery = OrdersDetailControllerMocks.deliveryForSave();
        order.setCustomer(this.customerRepo.save(customer));
        order.setDelivery(this.deliveryRepo.save(delivery));
        this.orderRepo.save(order);
        String url = String.format("%s%s", this.orderUrl, "/fetch/open");
        ResponseEntity<List<OrderDetailDto>> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                }
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<OrderDetailDto> act = response.getBody();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

    @Test
    public void findByOrderId() {
        readProductsByIds();
        readBillById();
        OrderDetailDto exp = OrdersDetailControllerMocks.orderForExpected();
        OrderDetail order = OrdersDetailControllerMocks.orderDetail();
        Customer customer = OrdersDetailControllerMocks.customer();
        Delivery delivery = OrdersDetailControllerMocks.deliveryForSave();
        order.setCustomer(this.customerRepo.save(customer));
        order.setDelivery(this.deliveryRepo.save(delivery));
        this.orderRepo.save(order);
        String url = String.format("%s%s", this.orderUrl, "/fetch?orderId=1");
        ResponseEntity<OrderDetailDto> response = this.restTemplate
                .getForEntity(url, OrderDetailDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        OrderDetailDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void findOrdersInTime() {
        readProductsByIds();
        readBillById();
        List<OrderDetailDto> exp = Lists.newArrayList(OrdersDetailControllerMocks.orderForExpected());
        OrderDetail order = OrdersDetailControllerMocks.orderDetail();
        Customer customer = OrdersDetailControllerMocks.customer();
        Delivery delivery = OrdersDetailControllerMocks.deliveryForSave();
        order.setCustomer(this.customerRepo.save(customer));
        order.setDelivery(this.deliveryRepo.save(delivery));
        this.orderRepo.save(order);
        LocalDateTime start = LocalDateTime.now()
                .minus(30L, ChronoField.MINUTE_OF_HOUR.getBaseUnit());
        LocalDateTime end = LocalDateTime.now();
        String url = String.format(
                "%s%s%s%s%s",
                this.orderUrl,
                "/fetch/open/?start=",
                start.toString(),
                "&end=",
                end.toString()
        );
        ResponseEntity<List<OrderDetailDto>> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<OrderDetailDto> act = response.getBody();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

    @Test
    public void findByUserId() {
        readProductsByIds();
        readBillById();
        List<OrderDetailDto> exp = Lists.newArrayList(OrdersDetailControllerMocks.orderForExpected());
        OrderDetail order = OrdersDetailControllerMocks.orderDetail();
        Customer customer = OrdersDetailControllerMocks.customer();
        Delivery delivery = OrdersDetailControllerMocks.deliveryForSave();
        order.setCustomer(this.customerRepo.save(customer));
        order.setDelivery(this.deliveryRepo.save(delivery));
        this.orderRepo.save(order);
        ResponseEntity<List<OrderDetailDto>> response = this.restTemplate.exchange(
                this.orderUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                }
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<OrderDetailDto> act = response.getBody();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

    @Test
    public void findByIds() {
        readProductsByIds();
        readBillById();
        List<OrderDetailDto> exp = Lists.newArrayList(OrdersDetailControllerMocks.orderForExpected());
        OrderDetail order = OrdersDetailControllerMocks.orderDetail();
        Customer customer = OrdersDetailControllerMocks.customer();
        Delivery delivery = OrdersDetailControllerMocks.deliveryForSave();
        order.setCustomer(this.customerRepo.save(customer));
        order.setDelivery(this.deliveryRepo.save(delivery));
        this.orderRepo.save(order);
        String url = String.format("%s%s", this.orderUrl, "/fetch?ids=1");
        ResponseEntity<List<OrderDetailDto>> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                }
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<OrderDetailDto> act = response.getBody();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

    @Test
    public void findBindingOrders() {
        readProductsByIds();
        readBillById();
        List<OrderDetailDto> exp = Lists.newArrayList(OrdersDetailControllerMocks.orderForExpected());
        OrderDetail order = OrdersDetailControllerMocks.orderDetail();
        Customer customer = OrdersDetailControllerMocks.customer();
        Delivery delivery = OrdersDetailControllerMocks.deliveryForSave();
        order.setCustomer(this.customerRepo.save(customer));
        order.setDelivery(this.deliveryRepo.save(delivery));
        this.orderRepo.save(order);
        String url = String.format("%s%s", this.orderUrl, "/fetch/binding/1");
        ResponseEntity<List<OrderDetailDto>> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                }
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<OrderDetailDto> act = response.getBody();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

    @Test
    public void updateOrderStatus() {
        OrderDetail orderForSave = OrdersDetailControllerMocks.orderDetail();
        Customer customer = OrdersDetailControllerMocks.customer();
        Delivery delivery = OrdersDetailControllerMocks.deliveryForSave();
        orderForSave.setCustomer(this.customerRepo.save(customer));
        orderForSave.setDelivery(this.deliveryRepo.save(delivery));
        this.orderRepo.save(orderForSave);
        String url = String.format("%s%s", this.orderUrl, "/edit/1/close");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.PUT, null, Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateOderPaid() {
        OrderDetail orderForSave = OrdersDetailControllerMocks.orderDetail();
        Customer customer = OrdersDetailControllerMocks.customer();
        Delivery delivery = OrdersDetailControllerMocks.deliveryForSave();
        orderForSave.setCustomer(this.customerRepo.save(customer));
        orderForSave.setDelivery(this.deliveryRepo.save(delivery));
        this.orderRepo.save(orderForSave);
        String url = String.format("%s%s", this.orderUrl, "/edit/paid/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.PUT, null, Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void remove() {
        removeBillById();
        OrderDetail orderForSave = OrdersDetailControllerMocks.orderDetail();
        Customer customer = OrdersDetailControllerMocks.customer();
        Delivery delivery = OrdersDetailControllerMocks.deliveryForSave();
        orderForSave.setCustomer(this.customerRepo.save(customer));
        orderForSave.setDelivery(this.deliveryRepo.save(delivery));
        this.orderRepo.save(orderForSave);
        String url = String.format("%s%s", this.orderUrl, "/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE, null, Void.class
        );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
