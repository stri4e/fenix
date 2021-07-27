package com.github.products.controllers.impl;

import com.github.products.ProductsConstant;
import com.github.products.dto.CategoryDto;
import com.github.products.entity.Category;
import com.github.products.repository.CategoryRepo;
import org.assertj.core.api.Assertions;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
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
public class CategoryControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CategoryRepo categoryRepo;

    private String categoryUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s",
                ProductsConstant.LOCALHOST, port, "/v1/categories"
        );
        this.categoryUrl = new URL(url).toString();
    }

    @Test
    @Sql(value = {"/products-schema.sql", "/products-data.sql"})
    public void givenCategories_whenFindAllCategories_thenReturnListOfCategoryDtoStatusOk() {
        ResponseEntity<List<CategoryDto>> response = this.restTemplate.exchange(
                this.categoryUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
        assertThat(
                response.getBody(),
                IsIterableContainingInAnyOrder.containsInAnyOrder(
                        CategoryControllerMocks.CATEGORIES_DTO.toArray()
                )
        );
    }

    @Test
    public void givenCategory_whenSaveCategory_thenSaveItAndReturnCategoryDtoStatusCreate() {
        Category exp = CategoryControllerMocks.expCategory();
        Category payload = CategoryControllerMocks.payloadCategory();
        String url = String.format("%s%s", this.categoryUrl, "/edit");
        ResponseEntity<Category> response = this.restTemplate.exchange(
                url, HttpMethod.POST, new HttpEntity<>(payload), Category.class
        );
        Category act = response.getBody();
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.CREATED));
        assertEquals(exp, act);
    }

    @Test
    public void givenCategoryName_whenFindByName_thenReturnCategoryStatusOk() {
        CategoryDto exp = new CategoryDto();
        String url = String.format(
                "%s%s%s", this.categoryUrl,
                "/fetch/", CategoryControllerMocks.CATEGORY_NAME
        );
        ResponseEntity<CategoryDto> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null, CategoryDto.class
        );
        CategoryDto act = response.getBody();
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
        Assertions.assertThat(act)
                .usingRecursiveComparison()
                .ignoringFields("createAt", "updateAt")
                .isEqualTo(exp);
    }

    @Test
    public void givenCategory_whenUpdateCategory_thenUpdateFieldStatusOk() {
        Category payload = CategoryControllerMocks.payloadCategoryForUpdate();
        Category exp = CategoryControllerMocks.payloadCategoryForUpdate();
        String url = String.format("%s%s", this.categoryUrl, "/edit");
        ResponseEntity<Category> response = this.restTemplate.exchange(
                url, HttpMethod.PUT, new HttpEntity<>(payload), Category.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
        Category act = this.categoryRepo.findByName(CategoryControllerMocks.CATEGORY_UPDATE_NAME);
        Assertions.assertThat(act)
                .usingRecursiveComparison()
                .ignoringFields("createAt", "updateAt")
                .isEqualTo(exp);
    }

    @Test
    @Sql(value = {"/products-schema.sql", "/products-data.sql"})
    public void givenCategoryID_whenRemove_thenUpdateStatusOnOffStatusNoContent() {
        String url = String.format("%s%s", this.categoryUrl, "/edit/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE, null, Void.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.NO_CONTENT));
    }

}
