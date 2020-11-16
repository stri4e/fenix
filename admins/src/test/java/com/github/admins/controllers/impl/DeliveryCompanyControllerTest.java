package com.github.admins.controllers.impl;

import com.github.admins.dto.CompanyDto;
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
public class DeliveryCompanyControllerTest extends DeliveryCompanyControllerBase {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String deliveryCompanyUrl;

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
        String url = String.format("%s%d%s", LOCALHOST, port, "/v1/delivery/company");
        this.deliveryCompanyUrl = new URL(url).toString();
    }

    @Test
    public void save() {
        createDeliveryCompany();
        CompanyDto request = DeliveryCompanyControllerMocks.request();
        CompanyDto exp = DeliveryCompanyControllerMocks.response();
        ResponseEntity<CompanyDto> response = this.restTemplate
                .postForEntity(this.deliveryCompanyUrl, request, CompanyDto.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        CompanyDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void findById() {
        readById();
        CompanyDto exp = DeliveryCompanyControllerMocks.response();
        String url = String.format("%s%s", this.deliveryCompanyUrl, "/1");
        ResponseEntity<CompanyDto> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null, CompanyDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        CompanyDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void update() {
        updateCompany();
        CompanyDto request = DeliveryCompanyControllerMocks.request();
        ResponseEntity<Void> response = this.restTemplate.exchange(
                this.deliveryCompanyUrl, HttpMethod.PUT,
                new HttpEntity<>(request), Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void delete() {
        removeCompany();
        String url = String.format("%s%s", this.deliveryCompanyUrl, "/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE, null, Void.class
        );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
