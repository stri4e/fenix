package com.github.geolocation.controllers.impl;

import com.github.geolocation.dto.GeolocationDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static com.github.geolocation.controllers.GeolocationConstant.LOCALHOST;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class GeolocationControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String geolocationUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s", LOCALHOST, port, "/v1"
        );
        this.geolocationUrl = new URL(url).toString();
    }

    @Test
    public void find() {
        GeolocationDto exp = GeolocationControllerMocks.response();
        ResponseEntity<GeolocationDto> response = this.restTemplate.exchange(
                this.geolocationUrl, HttpMethod.GET,
                null, GeolocationDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        GeolocationDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void findByParams() {
        GeolocationDto exp = GeolocationControllerMocks.response();
        String url = String.format("%s%s", this.geolocationUrl, "/edit?ip=1.179.128.0");
        ResponseEntity<GeolocationDto> response = this.restTemplate.exchange(
                url, HttpMethod.GET,
                null, GeolocationDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        GeolocationDto act = response.getBody();
        assertEquals(exp, act);
    }

}