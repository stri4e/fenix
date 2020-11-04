package com.github.admins.controllers.impl;

import com.github.admins.dto.FilterDto;
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
public class FiltersControllerTest extends FiltersControllerBase {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String filtersUrl;

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
        String url = String.format("%s%d%s", LOCALHOST, port, "/v1/filters");
        this.filtersUrl = new URL(url).toString();
    }

    @Test
    public void save() {
        createFilter();
        FilterDto request = FiltersControllerMocks.request();
        FilterDto exp = FiltersControllerMocks.response();
        String url = String.format(
                "%s%s", this.filtersUrl, "/subcategory1"
        );
        ResponseEntity<FilterDto> response = this.restTemplate.exchange(
                url, HttpMethod.POST, new HttpEntity<>(request), FilterDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        FilterDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void findById() {
        readById();
        FilterDto exp = FiltersControllerMocks.response();
        String url = String.format("%s%s", this.filtersUrl, "/1");
        ResponseEntity<FilterDto> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null, FilterDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        FilterDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void update() {
        updateFilter();
        FilterDto request = FiltersControllerMocks.request();
        ResponseEntity<Void> response = this.restTemplate.exchange(
                this.filtersUrl, HttpMethod.PUT, new HttpEntity<>(request), Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void remove() {
        removeFilter();
        String url = String.format("%s%s", this.filtersUrl, "/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE, null, Void.class
        );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
