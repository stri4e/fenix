package com.github.products.controllers.impl;

import com.github.products.dto.CommentDto;
import com.github.products.entity.Category;
import com.github.products.entity.Comment;
import com.github.products.entity.Product;
import com.github.products.entity.Specification;
import com.github.products.repository.CategoryRepo;
import com.github.products.repository.CommentRepo;
import com.github.products.repository.ProductRepo;
import com.github.products.repository.SpecificationRepo;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import static com.github.products.ProductsConstant.LOCALHOST;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class SpecificationControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private SpecificationRepo specificationRepo;

    private String specificationUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format("%s%d%s", LOCALHOST, port, "/v1/specification");
        this.specificationUrl = new URL(url).toString();
    }

    @Test
    public void save() {
        init();
        Specification exp = SpecificationControllerMocks.responsePayload();
        Specification payload = SpecificationControllerMocks.requestPayload();
        String url = String.format("%s%s", this.specificationUrl, "/edit");
        ResponseEntity<Specification> response = this.restTemplate.exchange(
                url, HttpMethod.POST, new HttpEntity<>(payload), Specification.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.CREATED));
        assertEquals(exp, response.getBody());
    }

    private void init() {
        Category categoryData = SpecificationControllerMocks.category();
        Category category = this.categoryRepo.save(categoryData);
        Product data = SpecificationControllerMocks.product();
        data.setCategory(category);
        this.productRepo.save(data);
    }

    @Test
    public void findById() {
        this.specificationRepo.save(SpecificationControllerMocks.requestPayload());
        Specification exp = SpecificationControllerMocks.responsePayload();
        String url = String.format("%s%s", this.specificationUrl, "/fetch/1");
        ResponseEntity<Specification> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null, Specification.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
        assertEquals(exp, response.getBody());
    }

    @Test
    public void update() {
        this.specificationRepo.save(SpecificationControllerMocks.requestPayload());
        Specification payload = SpecificationControllerMocks.specification();
        String url = String.format("%s%s", this.specificationUrl, "/edit");
        ResponseEntity<Specification> response = this.restTemplate.exchange(
                url, HttpMethod.PUT, new HttpEntity<>(payload), Specification.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
    }

}