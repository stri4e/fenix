package com.github.admins.controllers.impl;

import com.github.admins.dto.SpecificationDto;
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
public class SpecificationControllerTest extends SpecificationTestBase {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String specificationUrl;

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
        String url = String.format(
                "%s%d%s", LOCALHOST, port, "/v1/specification"
        );
        this.specificationUrl = new URL(url).toString();
    }

    @Test
    public void save() {
        create();
        String url = String.format("%s%s", this.specificationUrl, "/1");
        SpecificationDto exp = SpecificationControllerMocks.expSpec();
        SpecificationDto payload = SpecificationControllerMocks.payload();
        ResponseEntity<SpecificationDto> response = this.restTemplate
                .postForEntity(url, payload, SpecificationDto.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(exp, response.getBody());
    }

    @Test
    public void findById() {
        readById();
        String url = String.format("%s%s", this.specificationUrl, "/1");
        SpecificationDto exp = SpecificationControllerMocks.expSpec();
        ResponseEntity<SpecificationDto> response = this.restTemplate
                .getForEntity(url, SpecificationDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(exp, response.getBody());
    }

    @Test
    public void updateSpecification() {
        update();
        SpecificationDto payload = SpecificationControllerMocks.payloadWithId();
        ResponseEntity<Void> response = this.restTemplate.exchange(
                this.specificationUrl, HttpMethod.PUT,
                new HttpEntity<>(payload), Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}