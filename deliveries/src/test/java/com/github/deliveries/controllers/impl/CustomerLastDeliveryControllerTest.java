package com.github.deliveries.controllers.impl;

import com.github.deliveries.dto.CustomerLastDeliveryDto;
import com.github.deliveries.entity.Address;
import com.github.deliveries.entity.CustomerLastDelivery;
import com.github.deliveries.repository.AddressRepo;
import com.github.deliveries.repository.CustomerLastDeliveryRepo;
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

import static com.github.deliveries.controllers.DeliveryConstant.LOCALHOST;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class CustomerLastDeliveryControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomerLastDeliveryRepo deliveryRepo;

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
        Address address = CustomerLastDeliveryControllerMocks.addressForSave();
        CustomerLastDeliveryDto exp = CustomerLastDeliveryControllerMocks.deliveryForEquals();
        CustomerLastDelivery data = CustomerLastDeliveryControllerMocks.deliveryForSave()
                .address(this.addressRepo.save(address));
        this.deliveryRepo.save(data);
        ResponseEntity<CustomerLastDeliveryDto> response = this.restTemplate.exchange(
                this.deliveryUrl, HttpMethod.GET, null, CustomerLastDeliveryDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        CustomerLastDeliveryDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void save() {
        CustomerLastDeliveryDto exp = CustomerLastDeliveryControllerMocks.deliveryForEquals2();
        CustomerLastDeliveryDto payload = CustomerLastDeliveryControllerMocks.request();
        ResponseEntity<CustomerLastDeliveryDto> response = this.restTemplate.exchange(
                this.deliveryUrl, HttpMethod.POST,
                new HttpEntity<>(payload), CustomerLastDeliveryDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        CustomerLastDeliveryDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void updateDelivery() {
        CustomerLastDeliveryDto payload = CustomerLastDeliveryControllerMocks.deliveryForUpdate();
        CustomerLastDelivery data = CustomerLastDeliveryControllerMocks.deliveryForSave();
        this.deliveryRepo.save(data);
        ResponseEntity<Void> response = this.restTemplate.exchange(
                this.deliveryUrl, HttpMethod.PUT, new HttpEntity<>(payload), Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
