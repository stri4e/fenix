package com.github.admins.controllers.impl;

import com.github.admins.dto.CriteriaDto;
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
public class CriteriaControllerTest extends CriteriaControllerBase {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String criteriaUrl;

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
        String url = String.format("%s%d%s", LOCALHOST, port, "/v1/criteria");
        this.criteriaUrl = new URL(url).toString();
    }

    @Test
    public void saveToFilters() {
        createToFilters();
        CriteriaDto exp = CriteriaControllerMocks.response();
        CriteriaDto payload = CriteriaControllerMocks.request();
        String url = String.format("%s%s", this.criteriaUrl, "/to/filters/1");
        ResponseEntity<CriteriaDto> response = this.restTemplate.exchange(
                url, HttpMethod.POST, new HttpEntity<>(payload), CriteriaDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        CriteriaDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void saveToProducts() {
        createToProduct();
        String url = String.format("%s%s", this.criteriaUrl, "/to/products/1");
        ResponseEntity<CriteriaDto> response = this.restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(CriteriaControllerMocks.CRITERIA_IDS),
                CriteriaDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void findById() {
        readById();
        CriteriaDto exp = CriteriaControllerMocks.response();
        String url = String.format("%s%s", this.criteriaUrl, "/1");
        ResponseEntity<CriteriaDto> response = this.restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                CriteriaDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        CriteriaDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void update() {
        updateCriteria();
        CriteriaDto payload = CriteriaControllerMocks.request();
        ResponseEntity<CriteriaDto> response = this.restTemplate.exchange(
                this.criteriaUrl,
                HttpMethod.PUT,
                new HttpEntity<>(payload),
                CriteriaDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateInProducts() {
        updateInProductsServer();
        String url = String.format("%s%s", this.criteriaUrl, "/in/products/1/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url,
                HttpMethod.PUT,
                null,
                Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void removeInProducts() {
        removeInProductsServer();
        String url = String.format("%s%s", this.criteriaUrl, "/in/products/1/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                Void.class
        );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void updateInFilters() {
        updateInFiltersServer();
        String url = String.format("%s%s", this.criteriaUrl, "/in/filters/1/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url,
                HttpMethod.PUT,
                null,
                Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void removeInFilters() {
        removeInFiltersServer();
        String url = String.format("%s%s", this.criteriaUrl, "/in/filters/1/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                Void.class
        );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void remove() {
        delete();
        String url = String.format("%s%s", this.criteriaUrl, "/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                Void.class
        );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
