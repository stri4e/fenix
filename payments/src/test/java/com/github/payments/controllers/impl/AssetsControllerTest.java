package com.github.payments.controllers.impl;

import com.github.payments.dto.AssetDto;
import com.github.payments.entity.Asset;
import com.github.payments.repository.AssetsRepo;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class AssetsControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AssetsRepo assetsRepo;

    private String assetsUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s", PaymentsConstants.LOCALHOST, port, "/v1/assets"
        );
        this.assetsUrl = new URL(url).toString();
    }

    @Test
    public void findAssets() {
        List<AssetDto> exp = AssetsControllerMocks.ASSETS;
        List<Asset> asset = AssetsControllerMocks.ASSETS_FOR_SAVE;
        this.assetsRepo.saveAll(asset);
        ResponseEntity<List<AssetDto>> response = this.restTemplate.exchange(
                this.assetsUrl, HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<AssetDto> act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void findAssetsByStatus() {
        List<AssetDto> exp = AssetsControllerMocks.ASSETS;
        List<Asset> asset = AssetsControllerMocks.ASSETS_FOR_SAVE;
        this.assetsRepo.saveAll(asset);
        String url = String.format("%s%s", this.assetsUrl, "/fetch/all/on");
        ResponseEntity<List<AssetDto>> response = this.restTemplate.exchange(
                url, HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<AssetDto> act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void findAsset() {
        AssetDto exp = AssetsControllerMocks.response();
        this.assetsRepo.save(AssetsControllerMocks.forSave());
        String url = String.format("%s%s", this.assetsUrl, "/fetch/1");
        ResponseEntity<AssetDto> response = this.restTemplate.exchange(
                url, HttpMethod.GET,
                null, AssetDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        AssetDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void save() {
        AssetDto exp = AssetsControllerMocks.response();
        AssetDto payload = AssetsControllerMocks.request();
        String url = String.format("%s%s", this.assetsUrl, "/edit");
        ResponseEntity<AssetDto> response = this.restTemplate.exchange(
                url, HttpMethod.POST,
                new HttpEntity<>(payload), AssetDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        AssetDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void update() {
        this.assetsRepo.save(AssetsControllerMocks.forSave());
        AssetDto payload = AssetsControllerMocks.requestForUpdate();
        String url = String.format("%s%s", this.assetsUrl, "/edit");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.PUT,
                new HttpEntity<>(payload), Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void remove() {
        this.assetsRepo.save(AssetsControllerMocks.forSave());
        String url = String.format("%s%s", this.assetsUrl, "/edit/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE, null, Void.class
        );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
