package com.github.products.controllers.impl;

import com.github.products.ProductsConstant;
import com.github.products.dto.SubcategoryDto;
import com.github.products.entity.Category;
import com.github.products.entity.EntityStatus;
import com.github.products.entity.Subcategory;
import com.github.products.repository.CategoryRepo;
import com.github.products.repository.SubcategoryRepo;
import org.hamcrest.core.IsEqual;
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
import org.springframework.test.context.jdbc.Sql;
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
public class SubcategoryControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SubcategoryRepo subcategoryRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    private String subcategoryUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s",
                ProductsConstant.LOCALHOST, port, "/v1/subcategories"
        );
        this.subcategoryUrl = new URL(url).toString();
    }

    @Test
    @Sql(value = {"/products-schema.sql", "/products-data.sql"})
    public void givenSubcategoryAndCategoryName_whenCreate_thenReturnSubcategoryDto_Success() {
        var categoryName = "category-test-1";
        SubcategoryDto exp = SubcategoryControllerMocks.subcategoryEquals();
        SubcategoryDto payload = SubcategoryControllerMocks.subcategory();
        String url = String.format(
                "%s%s%s", this.subcategoryUrl, "/edit/",
                categoryName
        );
        ResponseEntity<SubcategoryDto> response = this.restTemplate.exchange(
                url, HttpMethod.POST, new HttpEntity<>(payload), SubcategoryDto.class
        );
        SubcategoryDto act = response.getBody();
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.CREATED));
        assertEquals(exp, act);
    }

    @Test
    public void findByName() {
        SubcategoryDto exp = SubcategoryControllerMocks.subcategoryEquals();
        Subcategory data = SubcategoryControllerMocks.subcategoryForSave();
        this.subcategoryRepo.save(data);
        String url = String.format(
                "%s%s%s", this.subcategoryUrl,
                "/fetch?name=", SubcategoryControllerMocks.SUBCATEGORY_NAME
        );
        ResponseEntity<SubcategoryDto> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null, SubcategoryDto.class
        );
        SubcategoryDto act = response.getBody();
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
        assertEquals(exp, act);
    }

    @Test
    public void findByStatus() {
        List<SubcategoryDto> exp = SubcategoryControllerMocks.subcategoriesEquals;
        this.subcategoryRepo.saveAll(SubcategoryControllerMocks.subcategories);
        String url = String.format(
                "%s%s%s", this.subcategoryUrl,
                "/fetch?status=", EntityStatus.on
        );
        ResponseEntity<List<SubcategoryDto>> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {}
        );
        List<SubcategoryDto> act = response.getBody();
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
        assertEquals(exp, act);
    }

    @Test
    public void update() {
        SubcategoryDto payload = SubcategoryControllerMocks.subcategoryForUpdate();
        Subcategory data = SubcategoryControllerMocks.subcategoryForSave();
        this.subcategoryRepo.save(data);
        String url = String.format("%s%s", this.subcategoryUrl, "/edit");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.PUT, new HttpEntity<>(payload), Void.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
    }

    @Test
    public void remove() {
        Subcategory data = SubcategoryControllerMocks.subcategoryForSave();
        this.subcategoryRepo.save(data);
        String url = String.format("%s%s", this.subcategoryUrl, "/edit/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE, null, Void.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.NO_CONTENT));
    }

}
