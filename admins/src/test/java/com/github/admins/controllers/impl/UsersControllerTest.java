package com.github.admins.controllers.impl;

import com.github.admins.dto.LockedDto;
import com.github.admins.dto.UserRegDto;
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
public class UsersControllerTest extends UsersControllerBase {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String userstUrl;

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
        String url = String.format("%s%d%s", LOCALHOST, port, "/v1/users");
        this.userstUrl = new URL(url).toString();
    }

    @Test
    public void adminsReg() {
        createAdmin();
        UserRegDto request = UsersControllerMocks.requestReg();
        String url = String.format("%s%s", this.userstUrl, "/admins/reg");
        ResponseEntity<Void> response = this.restTemplate
                .postForEntity(url, request, Void.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void managersReg() {
        createManager();
        UserRegDto request = UsersControllerMocks.requestReg();
        String url = String.format("%s%s", this.userstUrl, "/managers/reg");
        ResponseEntity<Void> response = this.restTemplate
                .postForEntity(url, request, Void.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void updateManagersIsLocked() {
        updateManagers();
        LockedDto request = UsersControllerMocks.requestLocked();
        String url = String.format("%s%s", this.userstUrl, "/locked");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.PUT,
                new HttpEntity<>(request), Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateAdminsIsLocked() {
        updateAdmins();
        LockedDto request = UsersControllerMocks.requestLocked();
        String url = String.format("%s%s", this.userstUrl, "/locked");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.PUT,
                new HttpEntity<>(request), Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
