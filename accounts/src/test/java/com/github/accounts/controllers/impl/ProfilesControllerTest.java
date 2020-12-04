package com.github.accounts.controllers.impl;

import com.github.accounts.dto.ProfileDto;
import com.github.accounts.repository.ProfilesRepo;
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

import static com.github.accounts.AccountsConstant.LOCALHOST;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class ProfilesControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProfilesRepo profilesRepo;

    private String profilesUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s", LOCALHOST, port, "/v1/profiles"
        );
        this.profilesUrl = new URL(url).toString();
    }

    @Test
    public void findContact() {
        ProfileDto exp = ProfilesControllerMocks.response();
        this.profilesRepo.save(ProfilesControllerMocks.profileForSave());
        String url = String.format("%s%s", this.profilesUrl, "/1");
        ResponseEntity<ProfileDto> response = this.restTemplate.exchange(
                url, HttpMethod.GET,
                null, ProfileDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ProfileDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void save() {
        ProfileDto exp = ProfilesControllerMocks.response();
        ProfileDto payload = ProfilesControllerMocks.request();
        ResponseEntity<ProfileDto> response = this.restTemplate.exchange(
                this.profilesUrl, HttpMethod.POST,
                new HttpEntity<>(payload), ProfileDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        ProfileDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void update() {
        this.profilesRepo.save(ProfilesControllerMocks.profileForSave());
        ProfileDto payload = ProfilesControllerMocks.requestForUpdate();
        ResponseEntity<Void> response = this.restTemplate.exchange(
                this.profilesUrl, HttpMethod.PUT,
                new HttpEntity<>(payload), Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
