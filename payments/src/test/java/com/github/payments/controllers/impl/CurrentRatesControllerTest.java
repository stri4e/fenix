package com.github.payments.controllers.impl;

import com.github.payments.dto.CurrentRateDto;
import com.github.payments.entity.CurrentRate;
import com.github.payments.entity.Rate;
import com.github.payments.repository.CurrentRateRepo;
import com.github.payments.repository.RateRepo;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
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
public class CurrentRatesControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CurrentRateRepo currentRateRepo;

    @Autowired
    private RateRepo rateRepo;

    private String currentUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s", PaymentsConstants.LOCALHOST, port, "/v1/rates"
        );
        this.currentUrl = new URL(url).toString();
    }

    @Test
    public void findAll() {
        List<CurrentRateDto> exp = List.of(CurrentRatesControllerMocks.currentRate());
        List<Rate> rates = this.rateRepo.saveAll(CurrentRatesControllerMocks.RATES);
        CurrentRate currentRate = CurrentRatesControllerMocks.currentRateForSave(rates);
        this.currentRateRepo.save(currentRate);
        ResponseEntity<List<CurrentRateDto>> response = this.restTemplate.exchange(
                this.currentUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<CurrentRateDto> act = response.getBody();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

}