package com.github.products.controllers.impl;

import com.github.products.ProductsConstant;
import com.github.products.dto.BrandDto;
import com.github.products.entity.Brand;
import com.github.products.entity.EntityStatus;
import com.github.products.repository.BrandRepo;
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
public class BrandControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BrandRepo brandRepo;

    private String brandUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s",
                ProductsConstant.LOCALHOST, port, "/v1/brands"
        );
        this.brandUrl = new URL(url).toString();
    }

    @Test
    public void save() {
        BrandDto exp = BrandControllerMocks.brandForEquals();
        Brand payload = BrandControllerMocks.brandForSave();
        String url = String.format(
                "%s%s", this.brandUrl, "/edit/"
        );
        ResponseEntity<BrandDto> response = this.restTemplate.exchange(
                url, HttpMethod.POST, new HttpEntity<>(payload), BrandDto.class
        );
        BrandDto act = response.getBody();
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.CREATED));
        assertEquals(exp, act);
    }

    @Test
    public void findByName() {
        BrandDto exp = BrandControllerMocks.brandForEquals();
        Brand payload = BrandControllerMocks.brandForSave();
        this.brandRepo.save(payload);
        String url = String.format(
                "%s%s%s",
                this.brandUrl,
                "/fetch/",
                BrandControllerMocks.BRAND_NAME
        );
        ResponseEntity<BrandDto> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null, BrandDto.class
        );
        BrandDto act = response.getBody();
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
        assertEquals(exp, act);
    }

    @Test
    public void findAllByStatus() {
        List<BrandDto> exp = BrandControllerMocks.BRANDS_FOR_EQUALS;
        this.brandRepo.saveAll(BrandControllerMocks.BRANDS_FOR_CREATE);
        String url = String.format(
                "%s%s%s",
                this.brandUrl,
                "/",
                EntityStatus.on
        );
        ResponseEntity<List<BrandDto>> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {}
        );
        List<BrandDto> act = response.getBody();
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
        assertEquals(exp, act);
    }

    @Test
    public void update() {
        BrandDto payload = BrandControllerMocks.brandForUpdate();
        Brand data = BrandControllerMocks.brandForSave();
        this.brandRepo.save(data);
        String url = String.format("%s%s", this.brandUrl, "/edit");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.PUT, new HttpEntity<>(payload), Void.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
    }

    @Test
    public void remove() {
        Brand data = BrandControllerMocks.brandForSave();
        this.brandRepo.save(data);
        String url = String.format("%s%s", this.brandUrl, "/edit/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE, null, Void.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.NO_CONTENT));
    }

}
