package com.github.products.controllers.impl;

import com.github.products.ProductsConstant;
import com.github.products.dto.CriteriaDto;
import com.github.products.entity.*;
import com.github.products.repository.*;
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
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class CriteriaControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CriteriaRepo criteriaRepo;

    @Autowired
    private FiltersRepo filtersRepo;

    @Autowired
    private BrandRepo brandRepo;

    @Autowired
    private SubcategoryRepo subcategoryRepo;

    @Autowired
    private ProductRepo productRepo;

    private String criteriaUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s",
                ProductsConstant.LOCALHOST, port, "/v1/criteria"
        );
        this.criteriaUrl = new URL(url).toString();
    }

    @Test
    public void saveToFilters() {
        CriteriaDto exp = CriteriaControllerMocks.responseForEquals();
        Filter filter = CriteriaControllerMocks.filterForSave();
        this.filtersRepo.save(filter);
        CriteriaDto payload = CriteriaControllerMocks.requestCriteria();
        String url = String.format("%s%s", this.criteriaUrl, "/edit/to/filters/1");
        ResponseEntity<CriteriaDto> response = this.restTemplate.exchange(
                url, HttpMethod.POST, new HttpEntity<>(payload), CriteriaDto.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.CREATED));
        CriteriaDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void saveToProducts() {
        Product product = CriteriaControllerMocks.productForCreate();
        Brand brand = this.brandRepo.save(CriteriaControllerMocks.brandForSave());
        Subcategory subcategory = this.subcategoryRepo.save(
                CriteriaControllerMocks.subcategoryForSave()
        );
        product.setSubcategory(subcategory);
        product.setBrand(brand);
        this.productRepo.save(product);
        List<Criteria> data = CriteriaControllerMocks.criteriaForSave;
        this.criteriaRepo.saveAll(data);
        String url = String.format("%s%s", this.criteriaUrl, "/edit/to/products/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.POST, new HttpEntity<>(CriteriaControllerMocks.IDS), Void.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.CREATED));
    }

    @Test
    public void findById() {
        CriteriaDto exp = CriteriaControllerMocks.responseForEquals();
        Criteria data = CriteriaControllerMocks.criteriaForSave();
        this.criteriaRepo.save(data);
        String url = String.format("%s%s", this.criteriaUrl, "/fetch/1");
        ResponseEntity<CriteriaDto> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null, CriteriaDto.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
        CriteriaDto act = response.getBody();
        assertNotNull(act);
        assertEquals(exp, act);
    }

    @Test
    public void update() {
        CriteriaDto payload = CriteriaControllerMocks.criteriaForUpdate();
        Criteria data = CriteriaControllerMocks.criteriaForSave();
        this.criteriaRepo.save(data);
        String url = String.format("%s%s", this.criteriaUrl, "/edit");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.PUT, new HttpEntity<>(payload), Void.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
    }

    @Test
    public void updateInProducts() {
        Product product = CriteriaControllerMocks.productForCreate();
        Brand brand = this.brandRepo.save(CriteriaControllerMocks.brandForSave());
        Subcategory subcategory = this.subcategoryRepo.save(
                CriteriaControllerMocks.subcategoryForSave()
        );
        product.setSubcategory(subcategory);
        product.setBrand(brand);
        this.productRepo.save(product);
        Criteria data = CriteriaControllerMocks.criteriaForSave();
        this.criteriaRepo.save(data);
        String url = String.format("%s%s", this.criteriaUrl, "/edit/in/products/1/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.PUT, null, Void.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
    }

    @Test
    public void removeInProducts() {
        Criteria data = CriteriaControllerMocks.criteriaForSave();
        Criteria criteria = this.criteriaRepo.save(data);
        Product product = CriteriaControllerMocks.productForCreate();
        Brand brand = this.brandRepo.save(CriteriaControllerMocks.brandForSave());
        Subcategory subcategory = this.subcategoryRepo.save(
                CriteriaControllerMocks.subcategoryForSave()
        );
        product.setSubcategory(subcategory);
        product.setBrand(brand);
        product.addCriteria(criteria);
        this.productRepo.save(product);
        String url = String.format("%s%s", this.criteriaUrl, "/edit/in/products/1/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE, null, Void.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.NO_CONTENT));
    }

    @Test
    public void updateInFilters() {
        Filter filter = CriteriaControllerMocks.filterForSave();
        this.filtersRepo.save(filter);
        Criteria data = CriteriaControllerMocks.criteriaForSave();
        this.criteriaRepo.save(data);
        String url = String.format("%s%s", this.criteriaUrl, "/edit/in/filters/1/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.PUT, null, Void.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
    }

    @Test
    public void removeInFilters() {
        Criteria data = CriteriaControllerMocks.criteriaForSave();
        Filter filter = CriteriaControllerMocks.filterForSave();
        Criteria criteria = this.criteriaRepo.save(data);
        filter.addCriteria(criteria);
        this.filtersRepo.save(filter);
        String url = String.format("%s%s", this.criteriaUrl, "/edit/in/filters/1/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE, null, Void.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.NO_CONTENT));
    }

    @Test
    public void remove() {
        Criteria data = CriteriaControllerMocks.criteriaForSave();
        this.criteriaRepo.save(data);
        String url = String.format("%s%s", this.criteriaUrl, "/edit/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE, null, Void.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.NO_CONTENT));
    }

}
