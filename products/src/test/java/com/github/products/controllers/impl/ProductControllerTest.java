package com.github.products.controllers.impl;

import com.github.products.ProductsConstant;
import com.github.products.entity.Category;
import com.github.products.entity.Product;
import com.github.products.repository.CategoryRepo;
import com.github.products.repository.ProductRepo;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class ProductControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ProductRepo productRepo;

    private String productUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s",
                ProductsConstant.LOCALHOST, port, "/v1"
        );
        this.productUrl = new URL(url).toString();
    }

    @Test
    public void save() {
        Product exp = ProductControllerMocks.responseProduct();
        Product payload = ProductControllerMocks.requestProduct();
        Category category = this.categoryRepo.save(ProductControllerMocks.categoryForSave());
        payload.setSubcategory(category);
        String url = String.format("%s%s", this.productUrl, "/edit");
        ResponseEntity<Product> response = this.restTemplate.exchange(
                url, HttpMethod.POST, new HttpEntity<>(payload), Product.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.CREATED));
        Product act = response.getBody();
        assertNotNull(act);
        assertEquals(exp.getId(), act.getId());
        assertEquals(exp.getName(), act.getName());
        assertEquals(exp.getQuantity(), act.getQuantity());
        assertEquals(exp.getDescription(), act.getDescription());
        assertEquals(exp.getPreviewImage(), act.getPreviewImage());
    }

    @Test
    public void findByParams() {
        Product product = ProductControllerMocks.requestProduct();
        Category category = this.categoryRepo.save(ProductControllerMocks.categoryForSave());
        product.setSubcategory(category);
        this.productRepo.save(product);
        Product exp = ProductControllerMocks.responseProduct();
        String url = String.format("%s%s", this.productUrl, "/fetch?id=1");
        ResponseEntity<Product> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null, Product.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
        Product act = response.getBody();
        assertNotNull(act);
        assertEquals(exp.getId(), act.getId());
        assertEquals(exp.getName(), act.getName());
        assertEquals(exp.getQuantity(), act.getQuantity());
        assertEquals(exp.getDescription(), act.getDescription());
        assertEquals(exp.getPreviewImage(), act.getPreviewImage());
    }

    @Test
    public void update() {
        Product product = ProductControllerMocks.requestProduct();
        Category category = this.categoryRepo.save(ProductControllerMocks.categoryForSave());
        product.setSubcategory(category);
        this.productRepo.save(product);
        Product payload = ProductControllerMocks.responseProductForUpdate();
        String url = String.format("%s%s", this.productUrl, "/edit");
        ResponseEntity<Product> response = this.restTemplate.exchange(
                url, HttpMethod.PUT, new HttpEntity<>(payload), Product.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
    }

    @Test
    public void updateStatus() {
        Product product = ProductControllerMocks.requestProduct();
        Category category = this.categoryRepo.save(ProductControllerMocks.categoryForSave());
        product.setSubcategory(category);
        this.productRepo.save(product);
        String url = String.format("%s%s", this.productUrl, "/edit/1/used");
        ResponseEntity<Product> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE, null, Product.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
    }

}