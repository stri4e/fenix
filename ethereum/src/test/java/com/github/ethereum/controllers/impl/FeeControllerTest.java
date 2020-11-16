package com.github.ethereum.controllers.impl;

import com.github.ethereum.EthereumConstant;
import com.github.ethereum.dto.FeeDto;
import com.github.ethereum.repository.FeeRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
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
public class FeeControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private FeeRepo feeRepo;

    private String feeUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s", EthereumConstant.LOCALHOST, port, "/v1/fee"
        );
        this.feeUrl = new URL(url).toString();
    }

    @Test
    public void findFee() {
        FeeDto exp = FeeControllerMocks.expected();
        this.feeRepo.save(FeeControllerMocks.fee());
        ResponseEntity<FeeDto> response = this.restTemplate.exchange(
                this.feeUrl,
                HttpMethod.GET, null,
                FeeDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        FeeDto act = response.getBody();
        assertEquals(exp, act);
    }

}
