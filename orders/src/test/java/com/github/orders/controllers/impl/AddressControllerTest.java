package com.github.orders.controllers.impl;

import com.github.orders.dto.AddressDto;
import com.github.orders.entity.Address;
import com.github.orders.entity.AddressType;
import com.github.orders.repository.AddressRepo;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import static org.junit.Assert.*;

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

    private String addressUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s", LOCALHOST, port, "/v1/addresses"
        );
        this.addressUrl = new URL(url).toString();
    }

    @Test
    public void findAddresses() {
        List<AddressDto> exp = AddressControllerMocks.addressesForEquals;
        this.addressRepo.saveAll(AddressControllerMocks.addressesForSave);
        ResponseEntity<List<AddressDto>> response = this.restTemplate.exchange(
                this.addressUrl, HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<AddressDto> act = response.getBody();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

    @Test
    public void findAddressType() {
        String url = String.format("%s%s", this.addressUrl, "/types");
        ResponseEntity<List<AddressType>> response = this.restTemplate.exchange(
                url, HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<AddressType> act = response.getBody();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(AddressType.values()));
    }

    @Test
    public void save() {
        AddressDto exp = AddressControllerMocks.response();
        AddressDto payload = AddressControllerMocks.request();
        ResponseEntity<AddressDto> response = this.restTemplate.exchange(
                this.addressUrl, HttpMethod.POST,
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