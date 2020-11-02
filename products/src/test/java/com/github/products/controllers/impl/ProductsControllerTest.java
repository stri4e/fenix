package com.github.products.controllers.impl;

import com.github.products.CustomPageImpl;
import com.github.products.ProductsConstant;
import com.github.products.dto.BoughtCountDto;
import com.github.products.dto.ProductDto;
import com.github.products.entity.Brand;
import com.github.products.entity.Criteria;
import com.github.products.entity.Product;
import com.github.products.entity.Subcategory;
import com.github.products.repository.BrandRepo;
import com.github.products.repository.CriteriaRepo;
import com.github.products.repository.ProductRepo;
import com.github.products.repository.SubcategoryRepo;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class ProductsControllerTest {

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

    @Autowired
    private CriteriaRepo criteriaRepo;

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
        this.brandRepo.save(ProductControllerMocks.brandForSave());
        Subcategory subcategoryData = ProductControllerMocks.subcategoryForSave();
        this.subcategoryRepo.save(subcategoryData);
        ProductDto exp = ProductControllerMocks.responseProduct();
        ProductDto payload = ProductControllerMocks.requestProduct();
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
    public void findByParamsId() {
        Product product = ProductControllerMocks.productForCreate();
        Brand brand = this.brandRepo.save(ProductControllerMocks.brandForSave());
        Subcategory subcategory = this.subcategoryRepo.save(ProductControllerMocks.subcategoryForSave());
        product.setSubcategory(subcategory);
        product.setBrand(brand);
        this.productRepo.save(product);
        ProductDto exp = ProductControllerMocks.responseProduct();
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
    public void findByParamsIds() {
        List<ProductDto> exp = ProductControllerMocks.products();
        List<Product> products = ProductControllerMocks.productsForSave();
        this.brandRepo.save(ProductControllerMocks.brandForSave());
        this.subcategoryRepo.save(ProductControllerMocks.subcategoryForSave());
        this.productRepo.saveAll(products);
        String url = String.format("%s%s", this.productUrl, "/fetch?ids=1&ids=2&ids=3&ids=4");
        ResponseEntity<List<ProductDto>> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
        List<ProductDto> act = response.getBody();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

    @Test
    public void findByParams() {
        List<ProductDto> exp = ProductControllerMocks.products();
        List<Product> products = ProductControllerMocks.productsForSave();
        this.brandRepo.save(ProductControllerMocks.brandForSave());
        this.subcategoryRepo.save(ProductControllerMocks.subcategoryForSave());
        this.productRepo.saveAll(products);
        String url = String.format("%s%s", this.productUrl, "/fetch");
        ResponseEntity<List<ProductDto>> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
        List<ProductDto> act = response.getBody();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

    @Test
    public void update() {
        Product product = ProductControllerMocks.productForCreate();
        Brand brand = this.brandRepo.save(ProductControllerMocks.brandForSave());
        Subcategory subcategory = this.subcategoryRepo.save(ProductControllerMocks.subcategoryForSave());
        product.setSubcategory(subcategory);
        product.setBrand(brand);
        this.productRepo.save(product);
        ProductDto payload = ProductControllerMocks.responseProductForUpdate();
        String url = String.format("%s%s", this.productUrl, "/edit");
        ResponseEntity<ProductDto> response = this.restTemplate.exchange(
                url, HttpMethod.PUT, new HttpEntity<>(payload), ProductDto.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
    }

    @Test
    public void updateStatus() {
        Product product = ProductControllerMocks.productForCreate();
        Brand brand = this.brandRepo.save(ProductControllerMocks.brandForSave());
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

    @Test
    public void updateBoughtCount() {
        Product product = ProductControllerMocks.productForCreate();
        Brand brand = this.brandRepo.save(ProductControllerMocks.brandForSave());
        Subcategory subcategory = this.subcategoryRepo.save(ProductControllerMocks.subcategoryForSave());
        product.setSubcategory(subcategory);
        product.setBrand(brand);
        this.productRepo.save(product);
        String url = String.format("%s%s", this.productUrl, "/edit/bought/count");
        List<BoughtCountDto> payload = List.of(ProductControllerMocks.boughtCount());
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.PUT, new HttpEntity<>(payload), Void.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
        Product data = this.productRepo.findById(1L).orElseThrow();
        Integer exp = data.getBoughtCount();
        Assert.assertEquals(2, exp.intValue());
    }

    @Test
    public void searchProduct() {
        List<ProductDto> exp = ProductControllerMocks.products();
        List<Product> products = ProductControllerMocks.productsForSave();
        this.brandRepo.save(ProductControllerMocks.brandForSave());
        this.subcategoryRepo.save(ProductControllerMocks.subcategoryForSave());
        this.productRepo.saveAll(products);
        String url = String.format("%s%s", this.productUrl, "?searchLine=phone for");
        ResponseEntity<List<ProductDto>> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
        List<ProductDto> act = response.getBody();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

    @Test
    public void findByPageBySubcategory() {
        List<ProductDto> exp = ProductControllerMocks.products();
        List<Product> products = ProductControllerMocks.productsForSave();
        this.brandRepo.save(ProductControllerMocks.brandForSave());
        this.subcategoryRepo.save(ProductControllerMocks.subcategoryForSave());
        this.productRepo.saveAll(products);
        String url = String.format("%s%s", this.productUrl, "/page/IPhone");
        ResponseEntity<CustomPageImpl<ProductDto>> response = this.restTemplate.exchange(
                url,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {}
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
        Page<ProductDto> act = response.getBody();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

    @Test
    public void findByPagePopular() {
        List<ProductDto> exp = ProductControllerMocks.products();
        List<Product> products = ProductControllerMocks.productsForSaveWithStatusOn();
        this.brandRepo.save(ProductControllerMocks.brandForSave());
        this.subcategoryRepo.save(ProductControllerMocks.subcategoryForSave());
        this.productRepo.saveAll(products);
        String url = String.format("%s%s", this.productUrl, "/page/popular");
        ResponseEntity<CustomPageImpl<ProductDto>> response = this.restTemplate.exchange(
                url,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {}
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
        Page<ProductDto> act = response.getBody();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

    @Test
    public void findByPageSubcategoryFilters() {
        List<Criteria> criteriaForSave = ProductControllerMocks.criteriaForSave;
        List<ProductDto> exp = ProductControllerMocks.products();
        List<Product> products = ProductControllerMocks.productsForSaveWithCriteria();
        List<Criteria> criteria = this.criteriaRepo.saveAll(criteriaForSave);
        this.brandRepo.save(ProductControllerMocks.brandForSave());
        this.subcategoryRepo.save(ProductControllerMocks.subcategoryForSave());
        List<Product> pp = new ArrayList<>();
        products.forEach(p -> {
            p.setCriteria(new HashSet<>(criteria));
            pp.add(p);
        });
        this.productRepo.saveAll(pp);
        String url = String.format(
                "%s%s",
                this.productUrl,
                "/page/IPhone/filters?criteria=1&criteria=2&criteria=3&criteria=4"
        );
        ResponseEntity<CustomPageImpl<ProductDto>> response = this.restTemplate.exchange(
                url,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {}
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
        Page<ProductDto> act = response.getBody();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

}
