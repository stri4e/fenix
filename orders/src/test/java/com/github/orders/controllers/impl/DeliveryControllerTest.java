package com.github.orders.controllers.impl;

import com.github.orders.dto.DeliveryDto;
import com.github.orders.entity.Address;
import com.github.orders.entity.Delivery;
import com.github.orders.repository.AddressRepo;
import com.github.orders.repository.DeliveryRepo;
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

import static com.github.orders.OrdersConstant.LOCALHOST;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class DeliveryControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DeliveryRepo deliveryRepo;

    @Autowired
    private AddressRepo addressRepo;

    private String deliveryUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s", LOCALHOST, port, "/v1/deliveries"
        );
        this.deliveryUrl = new URL(url).toString();
    }

    @Test
    public void findDelivery() {
        Address address = CustomerControllerMocks.addressForSave();
        DeliveryDto exp = DeliveryControllerMocks.deliveryForEquals();
        Delivery data = DeliveryControllerMocks.deliveryForSave()
                .address(this.addressRepo.save(address));
        this.deliveryRepo.save(data);
        ResponseEntity<DeliveryDto> response = this.restTemplate.exchange(
                this.deliveryUrl, HttpMethod.GET, null, DeliveryDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        DeliveryDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void save() {
        Address address = CustomerControllerMocks.addressForSave();
        this.addressRepo.save(address);
        DeliveryDto exp = DeliveryControllerMocks.deliveryForEquals();
        DeliveryDto payload = DeliveryControllerMocks.request();
        ResponseEntity<DeliveryDto> response = this.restTemplate.exchange(
                this.deliveryUrl, HttpMethod.POST,
                new HttpEntity<>(payload), DeliveryDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        DeliveryDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void updateDelivery() {
        DeliveryDto payload = DeliveryControllerMocks.deliveryForUpdate();
        Delivery data = DeliveryControllerMocks.deliveryForSave();
        this.deliveryRepo.save(data);
        ResponseEntity<Void> response = this.restTemplate.exchange(
                this.deliveryUrl, HttpMethod.PUT, new HttpEntity<>(payload), Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
