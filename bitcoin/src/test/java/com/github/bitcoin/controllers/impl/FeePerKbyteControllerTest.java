package com.github.bitcoin.controllers.impl;

import com.github.bitcoin.controllers.BitcoinConstant;
import com.github.bitcoin.dto.FeePerKbDto;
import com.github.bitcoin.entity.FeePerKb;
import com.github.bitcoin.repository.FeePerKbRepo;
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
public class FeePerKbyteControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private FeePerKbRepo feePerKbRepo;

    private String feePerkbUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s", BitcoinConstant.LOCALHOST, port, "/v1/fee/per/kb"
        );
        this.feePerkbUrl = new URL(url).toString();
    }

    @Test
    public void save() {
        FeePerKbDto payload = FeePerKbyteControllerMocks.request();
        FeePerKbDto exp = FeePerKbyteControllerMocks.response();
        String url = String.format("%s%s", this.feePerkbUrl, "/edit");
        ResponseEntity<FeePerKbDto> response = this.restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(payload),
                FeePerKbDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        FeePerKbDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void findActual() {
        FeePerKb data = FeePerKbyteControllerMocks.feePerKbForSave();
        this.feePerKbRepo.save(data);
        FeePerKbDto exp = FeePerKbyteControllerMocks.response();
        String url = String.format("%s%s", this.feePerkbUrl, "/fetch");
        ResponseEntity<FeePerKbDto> response = this.restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                FeePerKbDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        FeePerKbDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void update() {
        FeePerKb data = FeePerKbyteControllerMocks.feePerKbForSave();
        this.feePerKbRepo.save(data);
        FeePerKbDto payload = FeePerKbyteControllerMocks.requestForUpdate();
        String url = String.format("%s%s", this.feePerkbUrl, "/edit");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(payload),
                Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}