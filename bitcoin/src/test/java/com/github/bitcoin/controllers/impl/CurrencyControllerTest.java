package com.github.bitcoin.controllers.impl;

import com.github.bitcoin.controllers.BitcoinConstant;
import com.github.bitcoin.dto.CurrencyDto;
import com.github.bitcoin.entity.Currency;
import com.github.bitcoin.repository.CurrencyRepo;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class CurrencyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CurrencyRepo currencyRepo;

    private String currencyUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s", BitcoinConstant.LOCALHOST, port, "/v1/currency"
        );
        this.currencyUrl = new URL(url).toString();
    }

    @Test
    public void update() {
        Currency data = CurrencyControllerMocks.wrongCurrency();
        this.currencyRepo.save(data);
        CurrencyDto payload = CurrencyControllerMocks.request();
        String url = String.format("%s%s", this.currencyUrl, "/edit");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.PUT,
                new HttpEntity<>(payload), Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void find() {
        CurrencyDto exp = CurrencyControllerMocks.response();
        Currency data = CurrencyControllerMocks.currencyForSave();
        this.currencyRepo.save(data);
        ResponseEntity<CurrencyDto> response = this.restTemplate.exchange(
                this.currencyUrl, HttpMethod.GET,
                null, CurrencyDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        CurrencyDto act = response.getBody();
        assertEquals(exp, act);
    }

}
