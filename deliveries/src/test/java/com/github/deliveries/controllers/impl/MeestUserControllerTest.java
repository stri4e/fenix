package com.github.deliveries.controllers.impl;

import com.github.deliveries.dto.CompanyDto;
import com.github.deliveries.dto.MeestUserDto;
import com.github.deliveries.repository.MeestUserRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static com.github.deliveries.controllers.DeliveryConstant.LOCALHOST;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class MeestUserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MeestUserRepo meestUserRepo;

    private String meestUserUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s", LOCALHOST, port, "/v1/user/meest"
        );
        this.meestUserUrl = new URL(url).toString();
    }

    @Test
    public void save() {
        MeestUserDto payload = MeestUserControllerMocks.request();
        String url = String.format("%s%s", this.meestUserUrl, "/edit");
        ResponseEntity<CompanyDto> response = this.restTemplate.exchange(
                url, HttpMethod.POST,
                new HttpEntity<>(payload),
                new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void update() {
        this.meestUserRepo.save(MeestUserControllerMocks.meestUserForSave());
        MeestUserDto payload = MeestUserControllerMocks.requestForUpdate();
        String url = String.format("%s%s", this.meestUserUrl, "/edit");
        ResponseEntity<CompanyDto> response = this.restTemplate.exchange(
                url, HttpMethod.PUT,
                new HttpEntity<>(payload),
                new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void remove() {
        String url = String.format("%s%s", this.meestUserUrl, "/edit/1");
        ResponseEntity<CompanyDto> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}