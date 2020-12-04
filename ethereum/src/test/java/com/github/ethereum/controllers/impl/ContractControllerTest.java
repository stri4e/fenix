package com.github.ethereum.controllers.impl;

import com.github.ethereum.EthereumConstant;
import com.github.ethereum.dto.ContractDto;
import com.github.ethereum.entity.Contract;
import com.github.ethereum.repository.ContractRepo;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Assert;
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
public class ContractControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ContractRepo contractRepo;

    private String contractUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s", EthereumConstant.LOCALHOST, port, "/v1/contracts"
        );
        this.contractUrl = new URL(url).toString();
    }

    @Test
    public void createContract() {
        ContractDto payload = ContractControllerMocks.request();
        ContractDto exp = ContractControllerMocks.response();
        String url = String.format("%s%s", this.contractUrl, "/edit");
        ResponseEntity<ContractDto> response = this.restTemplate.exchange(
                url, HttpMethod.POST,
                new HttpEntity<>(payload),
                ContractDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        ContractDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void findAll() {
        List<ContractDto> exp = ContractControllerMocks.CONTRACT_FOR_EQUALS;
        this.contractRepo.saveAll(ContractControllerMocks.CONTRACT_FOR_SAVE);
        ResponseEntity<List<ContractDto>> response = this.restTemplate.exchange(
                this.contractUrl, HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<ContractDto> act = response.getBody();
        Assert.assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

    @Test
    public void update() {
        ContractDto payload = ContractControllerMocks.requestForUpdate();
        Contract data = ContractControllerMocks.contractForSave();
        this.contractRepo.save(data);
        String url = String.format("%s%s", this.contractUrl, "/edit");
        ResponseEntity<List<ContractDto>> response = this.restTemplate.exchange(
                url, HttpMethod.PUT,
                new HttpEntity<>(payload),
                new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void delete() {
        Contract data = ContractControllerMocks.contractForSave();
        this.contractRepo.save(data);
        String url = String.format("%s%s", this.contractUrl, "/edit/1");
        ResponseEntity<List<ContractDto>> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}