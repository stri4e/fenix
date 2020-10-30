package com.github.products.controllers.impl;

import com.github.products.ProductsConstant;
import com.github.products.dto.ProductDto;
import com.github.products.entity.Brand;
import com.github.products.entity.Product;
import com.github.products.entity.Subcategory;
import com.github.products.repository.BrandRepo;
import com.github.products.repository.ProductRepo;
import com.github.products.repository.SubcategoryRepo;
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
    private BrandRepo brandRepo;

    @Autowired
    private SubcategoryRepo subcategoryRepo;

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
        this.brandRepo.save(ProductControllerMocks.brand());
        Subcategory subcategoryData = ProductControllerMocks.subcategoryForSave();
        this.subcategoryRepo.save(subcategoryData);
        ProductDto exp = ProductControllerMocks.responseProduct1();
        ProductDto payload = ProductControllerMocks.requestProduct1();
        String url = String.format("%s%s", this.productUrl, "/edit/IPhone/OLX");
        ResponseEntity<ProductDto> response = this.restTemplate.exchange(
                url, HttpMethod.POST, new HttpEntity<>(payload), ProductDto.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.CREATED));
        ProductDto act = response.getBody();
        assertNotNull(act);
        assertEquals(exp, act);
    }

    @Test
    public void findByParams() {
        Product product = ProductControllerMocks.requestProduct();
        Brand brand = this.brandRepo.save(ProductControllerMocks.brand());
        Subcategory subcategory = this.subcategoryRepo.save(ProductControllerMocks.subcategoryForSave());
        product.setSubcategory(subcategory);
        product.setBrand(brand);
        this.productRepo.save(product);
        ProductDto exp = ProductControllerMocks.responseProduct1();
        String url = String.format("%s%s", this.productUrl, "/fetch?id=1");
        ResponseEntity<ProductDto> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null, ProductDto.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
        ProductDto act = response.getBody();
        assertNotNull(act);
        assertEquals(exp, act);
    }

    @Test
    public void update() {
        Product product = ProductControllerMocks.requestProduct();
        Brand brand = this.brandRepo.save(ProductControllerMocks.brand());
        Subcategory subcategory = this.subcategoryRepo.save(ProductControllerMocks.subcategoryForSave());
        product.setSubcategory(subcategory);
        product.setBrand(brand);
        this.productRepo.save(product);
        ProductDto payload = ProductControllerMocks.responseProductForUpdate1();
        String url = String.format("%s%s", this.productUrl, "/edit");
        ResponseEntity<ProductDto> response = this.restTemplate.exchange(
                url, HttpMethod.PUT, new HttpEntity<>(payload), ProductDto.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
    }

    @Test
    public void updateStatus() {
        Product product = ProductControllerMocks.requestProduct();
        Brand brand = this.brandRepo.save(ProductControllerMocks.brand());
        Subcategory subcategory = this.subcategoryRepo.save(ProductControllerMocks.subcategoryForSave());
        product.setSubcategory(subcategory);
        product.setBrand(brand);
        this.productRepo.save(product);
        String url = String.format("%s%s", this.productUrl, "/edit/1/on");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE, null, Void.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
    }

}
