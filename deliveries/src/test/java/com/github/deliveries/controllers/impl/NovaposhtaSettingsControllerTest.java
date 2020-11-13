package com.github.deliveries.controllers.impl;

import com.github.deliveries.dto.NovaposhtaSettingsDto;
import com.github.deliveries.entity.NovaposhtaSettings;
import com.github.deliveries.repository.NovaposhtaSettingsRepo;
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

import static com.github.deliveries.controllers.DeliveryConstant.LOCALHOST;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class NovaposhtaSettingsControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private NovaposhtaSettingsRepo novaposhtaSettingsRepo;

    private String novaposhtaUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s", LOCALHOST, port, "/v1/novaposhta"
        );
        this.novaposhtaUrl = new URL(url).toString();
    }

    @Test
    public void save() {
        NovaposhtaSettingsDto exp = NovaposhtaSettingsControllerMocks.response();
        NovaposhtaSettingsDto payload = NovaposhtaSettingsControllerMocks.request();
        String url = String.format("%s%s", this.novaposhtaUrl, "/edit");
        ResponseEntity<NovaposhtaSettingsDto> response = this.restTemplate.exchange(
                url, HttpMethod.POST,
                new HttpEntity<>(payload), NovaposhtaSettingsDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        NovaposhtaSettingsDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void find() {
        NovaposhtaSettingsDto exp = NovaposhtaSettingsControllerMocks.response();
        NovaposhtaSettings data = NovaposhtaSettingsControllerMocks.novaposhtaSettingsForSave();
        this.novaposhtaSettingsRepo.save(data);
        ResponseEntity<NovaposhtaSettingsDto> response = this.restTemplate.exchange(
                this.novaposhtaUrl, HttpMethod.GET,
                null, NovaposhtaSettingsDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        NovaposhtaSettingsDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void update() {
        NovaposhtaSettingsDto payload = NovaposhtaSettingsControllerMocks.requestForUpdate();
        NovaposhtaSettings data = NovaposhtaSettingsControllerMocks.novaposhtaSettingsForSave();
        this.novaposhtaSettingsRepo.save(data);
        String url = String.format("%s%s", this.novaposhtaUrl, "/edit");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.PUT,
                new HttpEntity<>(payload), Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void remove() {
        NovaposhtaSettings data = NovaposhtaSettingsControllerMocks.novaposhtaSettingsForSave();
        this.novaposhtaSettingsRepo.save(data);
        String url = String.format("%s%s", this.novaposhtaUrl, "/edit/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE,
                null, Void.class
        );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
