package com.github.orders.controllers.impl;

import com.github.orders.dto.CustomerDto;
import com.github.orders.entity.Address;
import com.github.orders.entity.Customer;
import com.github.orders.repository.AddressRepo;
import com.github.orders.repository.CustomerRepo;
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
public class CustomerControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private AddressRepo addressRepo;

    private String customerUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s", LOCALHOST, port, "/v1/customers"
        );
        this.customerUrl = new URL(url).toString();
    }

    @Test
    public void findCustomer() {
        CustomerDto exp = CustomerControllerMocks.customerForEquals();
        Address address = CustomerControllerMocks.addressForSave();
        Customer data = CustomerControllerMocks.customerForSave()
                .address(this.addressRepo.save(address));
        this.customerRepo.save(data);
        ResponseEntity<CustomerDto> response = this.restTemplate.exchange(
                this.customerUrl, HttpMethod.GET, null, CustomerDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        CustomerDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void save() {
        Address address = CustomerControllerMocks.addressForSave();
        this.addressRepo.save(address);
        CustomerDto exp = CustomerControllerMocks.customerForEquals();
        CustomerDto payload = CustomerControllerMocks.request();
        ResponseEntity<CustomerDto> response = this.restTemplate.exchange(
                this.customerUrl, HttpMethod.POST,
                new HttpEntity<>(payload), CustomerDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        CustomerDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void updateCustomer() {
        CustomerDto payload = CustomerControllerMocks.customerForUpdate();
        Customer data = CustomerControllerMocks.customerForSave();
        this.customerRepo.save(data);
        ResponseEntity<Void> response = this.restTemplate.exchange(
                this.customerUrl, HttpMethod.PUT, new HttpEntity<>(payload), Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
