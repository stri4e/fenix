package com.github.admins.controllers.impl;

import com.github.admins.dto.BrandDto;
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
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class BrandsControllerTest extends BrandsControllerBase {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String brandUrl;

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
        String url = String.format("%s%d%s", LOCALHOST, port, "/v1/brands");
        this.brandUrl = new URL(url).toString();
    }

    @Test
    public void save() {
        createBrand();
        BrandDto exp = BrandsControllerMocks.response();
        BrandDto payload = BrandsControllerMocks.request();
        ResponseEntity<BrandDto> response = this.restTemplate.exchange(
                this.brandUrl, HttpMethod.POST, new HttpEntity<>(payload), BrandDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        BrandDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void findByName() {
        readByName();
        BrandDto exp = BrandsControllerMocks.response();
        String url = String.format("%s%s", this.brandUrl, "/Apple");
        ResponseEntity<BrandDto> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null, BrandDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        BrandDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void update() {
        updateBrand();
        BrandDto payload = BrandsControllerMocks.request();
        ResponseEntity<BrandDto> response = this.restTemplate.exchange(
                this.brandUrl, HttpMethod.PUT, new HttpEntity<>(payload), BrandDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void remove() {
        removeBrand();
        String url = String.format("%s%s", this.brandUrl, "/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE, null, Void.class
        );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
