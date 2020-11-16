package com.github.payments.controllers.impl;

import com.github.payments.dto.AssetDto;
import com.github.payments.dto.PaymentTypesDto;
import com.github.payments.entity.Asset;
import com.github.payments.entity.PaymentTypes;
import com.github.payments.repository.AssetsRepo;
import com.github.payments.repository.PaymentTypesRepo;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class PaymentTypeControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PaymentTypesRepo paymentTypesRepo;

    private String paymentUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s", PaymentsConstants.LOCALHOST, port, "/v1/payment/type"
        );
        this.paymentUrl = new URL(url).toString();
    }

    @Test
    public void save() {
        PaymentTypesDto exp = PaymentTypeControllerMocks.paymentTypesForEquals();
        PaymentTypesDto payload = PaymentTypeControllerMocks.request();
        ResponseEntity<PaymentTypesDto> response = this.restTemplate.exchange(
                this.paymentUrl, HttpMethod.POST,
                new HttpEntity<>(payload), PaymentTypesDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        PaymentTypesDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void findAll() {
        List<PaymentTypesDto> exp = PaymentTypeControllerMocks.PAYMENT_TYPE_FOR_EQUALS;
        List<PaymentTypes> data = PaymentTypeControllerMocks.PAYMENT_TYPE_FOR_SAVE;
        this.paymentTypesRepo.saveAll(data);
        ResponseEntity<List<PaymentTypesDto>> response = this.restTemplate.exchange(
                this.paymentUrl, HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<PaymentTypesDto> act = response.getBody();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

    @Test
    public void findAllByStatus() {
        List<PaymentTypesDto> exp = PaymentTypeControllerMocks.PAYMENT_TYPE_FOR_EQUALS;
        List<PaymentTypes> data = PaymentTypeControllerMocks.PAYMENT_TYPE_FOR_SAVE;
        this.paymentTypesRepo.saveAll(data);
        String url = String.format("%s%s", this.paymentUrl, "/fetch/on");
        ResponseEntity<List<PaymentTypesDto>> response = this.restTemplate.exchange(
                url, HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<PaymentTypesDto> act = response.getBody();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

    @Test
    public void update() {
        this.paymentTypesRepo.save(PaymentTypeControllerMocks.paymentTypesForSave());
        PaymentTypesDto payload = PaymentTypeControllerMocks.paymentTypesForUpdate();
        String url = String.format("%s%s", this.paymentUrl, "/edit");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.PUT,
                new HttpEntity<>(payload), Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void remove() {
        this.paymentTypesRepo.save(PaymentTypeControllerMocks.paymentTypesForSave());
        String url = String.format("%s%s", this.paymentUrl, "/edit/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE, null, Void.class
        );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
