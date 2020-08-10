package com.github.admins.controllers.impl;

import com.github.admins.dto.CategoryDto;
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

import java.net.MalformedURLException;
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
public class CategoryControllerTest extends CategoryTestBase {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String categoryUrl;

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
    public void setUp() throws MalformedURLException {
        String url = String.format("%s%d%s", LOCALHOST, port, "/v1/category");
        this.categoryUrl = new URL(url).toString();
    }

    @Test
    public void createStatusCreated() {
        create();
        CategoryDto exp = CategoryControllerMocks.categoryDto();
        CategoryDto payload = CategoryControllerMocks.categoryDtoWithoutId();
        ResponseEntity<CategoryDto> response = this.restTemplate
                .postForEntity(this.categoryUrl, payload, CategoryDto.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        CategoryDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void createStatusBadRequest() {
        CategoryDto payload = CategoryControllerMocks.categoryDtoEmpty();
        ResponseEntity<CategoryDto> response = this.restTemplate
                .postForEntity(this.categoryUrl, payload, CategoryDto.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void findByNameStatusOk() {
        readByName();
        CategoryDto exp = CategoryControllerMocks.categoryDto();
        String url = String.format("%s%s", this.categoryUrl, "?name=Phone");
        ResponseEntity<CategoryDto> response = this.restTemplate.getForEntity(url, CategoryDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        CategoryDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void findAllStatusOk() {
        readAll();
        List<CategoryDto> exp = CategoryControllerMocks.CATEGORIES_DTO;
        ResponseEntity<List<CategoryDto>> response = this.restTemplate.exchange(
                this.categoryUrl,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                }
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<CategoryDto> act = response.getBody();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

    @Test
    public void updateCategory() {
        update();
        CategoryDto payload = CategoryControllerMocks.categoryDto();
        ResponseEntity<Void> response = this.restTemplate.exchange(
                this.categoryUrl, HttpMethod.PUT, new HttpEntity<>(payload), Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void removeCategory() {
        delete();
        String url = String.format("%s%s", this.categoryUrl, "/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE, null, Void.class
        );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
