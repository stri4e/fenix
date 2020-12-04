package com.github.admins.controllers.impl;

import com.github.admins.dto.ProductDto;
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
public class ProductsControllerTest extends ProductTestBase {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String productUrl;

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
                "%s%d%s", LOCALHOST, port, "/v1/product"
        );
        this.productUrl = new URL(url).toString();
    }

    @Test
    public void save() {
        create();
        String url = String.format("%s%s%s", this.productUrl, "/Phone", "/IPhone");
        ProductDto exp = ProductControllerMocks.productDto();
        ProductDto payload = ProductControllerMocks.requestProductPayload();
        ResponseEntity<ProductDto> response = this.restTemplate
                .postForEntity(url, payload, ProductDto.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        ProductDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void findById() {
        readById();
        ProductDto exp = ProductControllerMocks.productDto();
        String url = String.format("%s%s", this.productUrl, "/1");
        ResponseEntity<ProductDto> response = this.restTemplate
                .getForEntity(url, ProductDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ProductDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void findAllUnPublish() {
        readAllUnpublish();
        String url = String.format("%s%s", this.productUrl, "/un-publish");
        List<ProductDto> exp = ProductControllerMocks.PRODUCTS;
        ResponseEntity<List<ProductDto>> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<ProductDto> act = response.getBody();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

    @Test
    public void updateProduct() {
        update();
        ProductDto payload = ProductControllerMocks.getProductDtoForUpdate();
        ResponseEntity<Void> response = this.restTemplate.exchange(
                this.productUrl, HttpMethod.PUT, new HttpEntity<>(payload),
                new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void changeStatusProduct() {
        changeStatus();
        String url = String.format("%s%s", this.productUrl, "/1/on");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE, null,
                new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
