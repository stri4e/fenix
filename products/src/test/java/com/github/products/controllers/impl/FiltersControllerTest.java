package com.github.products.controllers.impl;

import com.github.products.ProductsConstant;
import com.github.products.dto.FilterDto;
import com.github.products.entity.Filter;
import com.github.products.entity.Subcategory;
import com.github.products.repository.FiltersRepo;
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
public class FiltersControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SubcategoryRepo subcategoryRepo;

    @Autowired
    private FiltersRepo filtersRepo;

    private String filtersUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s",
                ProductsConstant.LOCALHOST, port, "/v1/filters"
        );
        this.filtersUrl = new URL(url).toString();
    }

    @Test
    public void save() {
        FilterDto exp = FiltersControllerMocks.filterEquals();
        Subcategory subcategory = FiltersControllerMocks.subcategoryForSave();
        this.subcategoryRepo.save(subcategory);
        FilterDto payload = FiltersControllerMocks.requestFilterPayload();
        String url = String.format(
                "%s%s%s", this.filtersUrl, "/edit/",
                FiltersControllerMocks.SUBCATEGORY_NAME
        );
        ResponseEntity<FilterDto> response = this.restTemplate.exchange(
                url, HttpMethod.POST, new HttpEntity<>(payload), FilterDto.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.CREATED));
        FilterDto act = response.getBody();
        assertNotNull(act);
        assertEquals(exp, act);
    }

    @Test
    public void findById() {
        FilterDto exp = FiltersControllerMocks.filterEquals();
        Filter data = FiltersControllerMocks.filterForSave();
        this.filtersRepo.save(data);
        String url = String.format(
                "%s%s", this.filtersUrl, "/fetch/1"
        );
        ResponseEntity<FilterDto> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null, FilterDto.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
        FilterDto act = response.getBody();
        assertNotNull(act);
        assertEquals(exp, act);
    }

    @Test
    public void update() {
        FilterDto payload = FiltersControllerMocks.requestFilterPayloadForUpdate();
        Filter data = FiltersControllerMocks.filterForSave();
        this.filtersRepo.save(data);
        String url = String.format(
                "%s%s", this.filtersUrl, "/edit"
        );
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.PUT, new HttpEntity<>(payload), Void.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
    }

    @Test
    public void remove() {
        Filter data = FiltersControllerMocks.filterForSave();
        this.filtersRepo.save(data);
        String url = String.format(
                "%s%s", this.filtersUrl, "/edit/1"
        );
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE, null, Void.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.NO_CONTENT));
    }

}
