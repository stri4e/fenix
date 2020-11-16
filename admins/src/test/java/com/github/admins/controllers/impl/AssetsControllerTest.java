package com.github.admins.controllers.impl;

import com.github.admins.dto.AssetDto;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.util.List;

import static com.github.admins.AdminsConstant.LOCALHOST;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class AssetsControllerTest extends AssetsControllerBase {

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
        String url = String.format("%s%d%s", LOCALHOST, port, "/v1/payments/assets");
        this.accountantUrl = new URL(url).toString();
    }

    @Test
    public void findAssetsByStatus() {
        readByStatus();
        List<AssetDto> exp = AssetsControllerMocks.ASSETS;
        String url = String.format("%s%s", this.accountantUrl, "/on");
        ResponseEntity<List<AssetDto>> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                }
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<AssetDto> act = response.getBody();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

    @Test
    public void findAsset() {
        readById();
        AssetDto exp = AssetsControllerMocks.response();
        String url = String.format("%s%s", this.accountantUrl, "/one/1");
        ResponseEntity<AssetDto> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null, AssetDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        AssetDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void save() {
        createAsset();
        AssetDto exp = AssetsControllerMocks.response();
        AssetDto payload = AssetsControllerMocks.request();
        ResponseEntity<AssetDto> response = this.restTemplate.exchange(
                this.accountantUrl, HttpMethod.POST, new HttpEntity<>(payload), AssetDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        AssetDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void update() {
        updateAsset();
        AssetDto payload = AssetsControllerMocks.request();
        ResponseEntity<AssetDto> response = this.restTemplate.exchange(
                this.accountantUrl, HttpMethod.PUT, new HttpEntity<>(payload), AssetDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void remove() {
        removeAsset();
        String url = String.format("%s%s", this.accountantUrl, "/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE, null, Void.class
        );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
