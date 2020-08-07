package com.github.admins.controllers.impl;

import com.github.admins.dto.ProductDto;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
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
public class CustomProductControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String productUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s", LOCALHOST, port, "/v1/product"
        );
        this.productUrl = new URL(url).toString();
    }

    @Test
    public void save() {
        String url = String.format("%s%s", this.productUrl, "/Phone");
        ProductDto exp = CustomProductControllerMocks.productDto();
        ProductDto payload = CustomProductControllerMocks.productDtoWithoutId();
        ResponseEntity<ProductDto> response = this.restTemplate
                .postForEntity(url, payload, ProductDto.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        ProductDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void findById() {
        ProductDto exp = CustomProductControllerMocks.productDto();
        String url = String.format("%s%s", this.productUrl, "/1");
        ResponseEntity<ProductDto> response = this.restTemplate
                .getForEntity(url, ProductDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ProductDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void findAllUnPublish() {
        String url = String.format("%s%s", this.productUrl, "/un-publish");
        List<ProductDto> exp = CustomProductControllerMocks.PRODUCTS;
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
        ProductDto payload = CustomProductControllerMocks.getProductDtoForUpdate();
        ResponseEntity<Void> response = this.restTemplate.exchange(
                this.productUrl, HttpMethod.PUT, new HttpEntity<>(payload),
                new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void changeStatusProduct() {
        String url = String.format("%s%s", this.productUrl, "/1/used");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE, null,
                new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
