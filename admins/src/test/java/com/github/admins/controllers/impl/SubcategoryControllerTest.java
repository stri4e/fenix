package com.github.admins.controllers.impl;

import com.github.admins.dto.AccountantDto;
import com.github.admins.dto.FilterDto;
import com.github.admins.dto.SubcategoryDto;
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
public class SubcategoryControllerTest extends SubcategoryControllerBase {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String subcategoryUrl;

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
        String url = String.format("%s%d%s", LOCALHOST, port, "/v1/subcategory");
        this.subcategoryUrl = new URL(url).toString();
    }

    @Test
    public void save() {
        createSubcategory();
        SubcategoryDto request = SubcategoryControllerMocks.request();
        SubcategoryDto exp = SubcategoryControllerMocks.response();
        String url = String.format("%s%s", this.subcategoryUrl, "/omg");
        ResponseEntity<SubcategoryDto> response = this.restTemplate
                .postForEntity(url, request, SubcategoryDto.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        SubcategoryDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void findByName() {
        readByName();
        SubcategoryDto exp = SubcategoryControllerMocks.response();
        String url = String.format("%s%s", this.subcategoryUrl, "?name=omg");
        ResponseEntity<SubcategoryDto> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null, SubcategoryDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        SubcategoryDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void update() {
        updateSubcategory();
        SubcategoryDto request = SubcategoryControllerMocks.request();
        ResponseEntity<Void> response = this.restTemplate.exchange(
                this.subcategoryUrl, HttpMethod.PUT, new HttpEntity<>(request), Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void remove() {
        removeSubcategory();
        String url = String.format("%s%s", this.subcategoryUrl, "/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE, null, Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
