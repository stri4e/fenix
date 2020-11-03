package com.github.orders.controllers.impl;

import com.github.orders.dto.CompanyDto;
import com.github.orders.repository.CompanyRepo;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
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

import static com.github.orders.OrdersConstant.LOCALHOST;
import static com.github.orders.controllers.impl.DeliveryCompaniesMocks.COMPANIES_FOR_EQUALS;
import static com.github.orders.controllers.impl.DeliveryCompaniesMocks.COMPANIES_FOR_SAVE;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class DeliveryCompaniesControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CompanyRepo companyRepo;

    private String deliveryUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s", LOCALHOST, port, "/v1/delivery/company"
        );
        this.deliveryUrl = new URL(url).toString();
    }

    @Test
    public void findAll() {
        this.companyRepo.saveAll(COMPANIES_FOR_SAVE);
        ResponseEntity<List<CompanyDto>> response = this.restTemplate.exchange(
                this.deliveryUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<CompanyDto> act = response.getBody();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(
                COMPANIES_FOR_EQUALS.toArray())
        );
    }

    @Test
    public void save() {
        CompanyDto payload = DeliveryCompaniesMocks.requestCompany();
        CompanyDto exp = DeliveryCompaniesMocks.companyForEquals();
        String url = String.format("%s%s", this.deliveryUrl, "/edit");
        ResponseEntity<CompanyDto> response = this.restTemplate.exchange(
                url, HttpMethod.POST,
                new HttpEntity<>(payload),
                new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        CompanyDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void findById() {
        CompanyDto exp = DeliveryCompaniesMocks.companyForEquals();
        this.companyRepo.save(DeliveryCompaniesMocks.companyForSave());
        String url = String.format("%s%s", this.deliveryUrl, "/fetch/1");
        ResponseEntity<CompanyDto> response = this.restTemplate.exchange(
                url, HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        CompanyDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void update() {
        CompanyDto payload = DeliveryCompaniesMocks.requestCompanyForUpdate();
        this.companyRepo.save(DeliveryCompaniesMocks.companyForSave());
        String url = String.format("%s%s", this.deliveryUrl, "/edit");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.PUT,
                new HttpEntity<>(payload),
                Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void remove() {
        this.companyRepo.save(DeliveryCompaniesMocks.companyForSave());
        String url = String.format("%s%s", this.deliveryUrl, "/edit/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE,
                null,
                Void.class
        );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
