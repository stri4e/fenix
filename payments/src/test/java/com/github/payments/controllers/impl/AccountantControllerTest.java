package com.github.payments.controllers.impl;

import com.github.payments.dto.AccountantDto;
import com.github.payments.entity.Accountant;
import com.github.payments.repository.AccountantRepo;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class AccountantControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AccountantRepo accountantRepo;

    private String accountantUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s", PaymentsConstants.LOCALHOST, port, "/v1/accountant"
        );
        this.accountantUrl = new URL(url).toString();
    }

    @Test
    public void save() {
        AccountantDto payload = AccountantControllerMocks.request();
        AccountantDto exp = AccountantControllerMocks.response();
        String url = String.format("%s%s", this.accountantUrl, "/edit");
        ResponseEntity<AccountantDto> response = this.restTemplate.exchange(
                url, HttpMethod.POST,
                new HttpEntity<>(payload),
                AccountantDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        AccountantDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void findActive() {
        Accountant data = AccountantControllerMocks.forSave();
        this.accountantRepo.save(data);
        AccountantDto exp = AccountantControllerMocks.response();
        ResponseEntity<AccountantDto> response = this.restTemplate.exchange(
                this.accountantUrl, HttpMethod.GET, null, AccountantDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        AccountantDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void update() {
        Accountant data = AccountantControllerMocks.forSave();
        this.accountantRepo.save(data);
        AccountantDto payload = AccountantControllerMocks.requestForUpdate();
        String url = String.format("%s%s", this.accountantUrl, "/edit");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.PUT, new HttpEntity<>(payload), Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void remove() {
        Accountant data = AccountantControllerMocks.forSave();
        this.accountantRepo.save(data);
        String url = String.format("%s%s", this.accountantUrl, "/edit/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE, null, Void.class
        );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
