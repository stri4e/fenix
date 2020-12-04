package com.github.products.controllers.impl;

import com.github.products.dto.CommentDto;
import com.github.products.entity.Brand;
import com.github.products.entity.Product;
import com.github.products.entity.Subcategory;
import com.github.products.repository.BrandRepo;
import com.github.products.repository.CommentRepo;
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

import static com.github.products.ProductsConstant.LOCALHOST;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class CommentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SubcategoryRepo subcategoryRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private BrandRepo brandRepo;

    private String commentUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format("%s%d%s", LOCALHOST, port, "/v1/comments/");
        this.commentUrl = new URL(url).toString();
    }

    @Test
    public void saveComment() {
        init();
        CommentDto exp = CommentControllerMocks.responsePayload();
        CommentDto payload = CommentControllerMocks.requestPayload();
        String url = String.format("%s%s", this.commentUrl, "/1");
        ResponseEntity<CommentDto> response = this.restTemplate.exchange(
                url, HttpMethod.POST, new HttpEntity<>(payload), CommentDto.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.CREATED));
        assertEquals(exp, response.getBody());
    }

    private void init() {
        Brand brand = this.brandRepo.save(SpecificationControllerMocks.brand());
        Subcategory subcategoryData = SpecificationControllerMocks.subcategoryForSave();
        Subcategory subcategory = this.subcategoryRepo.save(subcategoryData);
        Product data = SpecificationControllerMocks.product();
        data.setSubcategory(subcategory);
        data.setBrand(brand);
        this.productRepo.save(data);
    }

    @Test
    public void findById() {
        this.commentRepo.save(CommentControllerMocks.commentForSave());
        CommentDto exp = CommentControllerMocks.responsePayload();
        String url = String.format("%s%s", this.commentUrl, "/fetch/1");
        ResponseEntity<CommentDto> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null, CommentDto.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.OK));
        assertEquals(exp, response.getBody());
    }

    @Test
    public void remove() {
        this.commentRepo.save(CommentControllerMocks.commentForSave());
        String url = String.format("%s%s", this.commentUrl, "/edit/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE, null, Void.class
        );
        assertThat(response.getStatusCode(), IsEqual.equalTo(HttpStatus.NO_CONTENT));
    }

}
