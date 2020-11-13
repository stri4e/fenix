package com.github.deliveries.controllers.impl;

import com.github.deliveries.dto.MeestSettingsDto;
import com.github.deliveries.entity.MeestSession;
import com.github.deliveries.entity.MeestSettings;
import com.github.deliveries.entity.MeestUser;
import com.github.deliveries.repository.MeestSessionRepo;
import com.github.deliveries.repository.MeestSettingsRepo;
import com.github.deliveries.repository.MeestUserRepo;
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
public class MeestSettingsControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MeestUserRepo meestUserRepo;

    @Autowired
    private MeestSettingsRepo meestSettingsRepo;

    @Autowired
    private MeestSessionRepo meestSessionRepo;

    private String meestSettingsUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s", LOCALHOST, port, "/v1/meest"
        );
        this.meestSettingsUrl = new URL(url).toString();
    }

    @Test
    public void save() {
        MeestSettingsDto exp = MeestSettingsControllerMocks.response();
        MeestSettingsDto payload = MeestSettingsControllerMocks.request();
        String url = String.format("%s%s", this.meestSettingsUrl, "/edit");
        ResponseEntity<MeestSettingsDto> response = this.restTemplate.exchange(
                url, HttpMethod.POST,
                new HttpEntity<>(payload),
                MeestSettingsDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        MeestSettingsDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void clientSessionFirstInit() {
        MeestUser user = MeestSettingsControllerMocks.meestUserForSave();
        MeestSettingsDto exp = MeestSettingsControllerMocks.responseForNewSession();
        MeestSettings data = MeestSettingsControllerMocks.meestSettingsForSave();
        this.meestSettingsRepo.save(data);
        this.meestUserRepo.save(user);
        ResponseEntity<MeestSettingsDto> response = this.restTemplate.exchange(
                this.meestSettingsUrl, HttpMethod.POST,
                null,
                MeestSettingsDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        MeestSettingsDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void clientSessionExpired() {
        MeestUser user = MeestSettingsControllerMocks.meestUserForSave();
        MeestSettingsDto exp = MeestSettingsControllerMocks.responseForNewSession();
        MeestSettings data = MeestSettingsControllerMocks.meestSettingsForSave();
        MeestSession meestSession = this.meestSessionRepo.save(MeestSettingsControllerMocks.meestSession());
        data.setSession(meestSession);
        this.meestSettingsRepo.save(data);
        this.meestUserRepo.save(user);
        ResponseEntity<MeestSettingsDto> response = this.restTemplate.exchange(
                this.meestSettingsUrl, HttpMethod.POST,
                null,
                MeestSettingsDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        MeestSettingsDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void update() {
        MeestSettingsDto payload = MeestSettingsControllerMocks.requestForUpdate();
        MeestSettings data = MeestSettingsControllerMocks.meestSettingsForSave();
        this.meestSettingsRepo.save(data);
        String url = String.format("%s%s", this.meestSettingsUrl, "/edit");
        ResponseEntity<MeestSettingsDto> response = this.restTemplate.exchange(
                url, HttpMethod.PUT,
                new HttpEntity<>(payload),
                MeestSettingsDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void remove() {
        MeestSettings data = MeestSettingsControllerMocks.meestSettingsForSave();
        this.meestSettingsRepo.save(data);
        String url = String.format("%s%s", this.meestSettingsUrl, "/edit/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE,
                null,
                Void.class
        );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
