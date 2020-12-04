package com.github.customers.controllers.impl;

import com.github.customers.dto.AddressDto;
import com.github.customers.entity.Address;
import com.github.customers.repository.AddressRepo;
import com.github.customers.repository.CustomerRepo;
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

import static com.github.customers.CustomersConstant.LOCALHOST;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class AddressControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private CustomerRepo customerRepo;

    private String addressUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s", LOCALHOST, port, "/v1/addresses"
        );
        this.addressUrl = new URL(url).toString();
    }

    @Test
    public void findAddress() {
        AddressDto exp = AddressControllerMocks.addressForEquals();
        this.addressRepo.save(AddressControllerMocks.addressForSave());
        String url = String.format("%s%s", this.addressUrl, "/1");
        ResponseEntity<AddressDto> response = this.restTemplate.exchange(
                url, HttpMethod.GET,
                null, AddressDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        AddressDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void save() {
        AddressDto exp = AddressControllerMocks.response();
        AddressDto payload = AddressControllerMocks.request();
        this.customerRepo.save(AddressControllerMocks.customerForSave());
        String url = String.format("%s%s", this.addressUrl, "/1");
        ResponseEntity<AddressDto> response = this.restTemplate.exchange(
                url, HttpMethod.POST,
                new HttpEntity<>(payload), AddressDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        AddressDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void update() {
        Address data = AddressControllerMocks.addressForSave();
        this.addressRepo.save(data);
        AddressDto payload = AddressControllerMocks.requestForUpdate();
        ResponseEntity<Void> response = this.restTemplate.exchange(
                this.addressUrl, HttpMethod.PUT,
                new HttpEntity<>(payload), Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
