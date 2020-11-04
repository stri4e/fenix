package com.github.admins.controllers.impl;

import com.github.admins.dto.AccountantDto;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static com.github.admins.AdminsConstant.LOCALHOST;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class AccountantControllerTest extends AccountantControllerBase {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String accountantUrl;

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
        String url = String.format("%s%d%s", LOCALHOST, port, "/v1/payments/accountant");
        this.accountantUrl = new URL(url).toString();
    }

    @Test
    public void save() {
        createAccountant();
        AccountantDto request = AccountantControllerMocks.request();
        AccountantDto exp = AccountantControllerMocks.response();
        ResponseEntity<AccountantDto> response = this.restTemplate
                .postForEntity(this.accountantUrl, request, AccountantDto.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        AccountantDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void update() {
        updateAccountant();
        AccountantDto request = AccountantControllerMocks.request();
        ResponseEntity<Void> response = this.restTemplate.exchange(
                this.accountantUrl, HttpMethod.PUT,
                new HttpEntity<>(request), Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void remove() {
        removeAccountant();
        String url = String.format("%s%s", this.accountantUrl, "/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE,
                null, Void.class
        );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
