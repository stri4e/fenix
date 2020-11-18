package com.github.accounts.controllers.impl;

import com.github.accounts.dto.ProfileDto;
import com.github.accounts.entity.Address;
import com.github.accounts.entity.Contact;
import com.github.accounts.entity.Profile;
import com.github.accounts.entity.View;
import com.github.accounts.repository.*;
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
public class ViewsControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AccountsRepo accountsRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private ProfilesRepo profilesRepo;

    @Autowired
    private ContactsRepo contactsRepo;

    @Autowired
    private ViewsRepo viewsRepo;

    private String viewsUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s", LOCALHOST, port, "/v1/views"
        );
        this.viewsUrl = new URL(url).toString();
    }

    @Test
    public void updateViews() {
        Address address = this.addressRepo.save(ViewsControllerMocks.addressForSave());
        Contact contact = this.contactsRepo.save(ViewsControllerMocks.contactForSave());
        Profile profile = this.profilesRepo.save(ViewsControllerMocks.profileForSave());
        this.accountsRepo.save(ViewsControllerMocks.accountForSave(profile, contact, address));
        String url = String.format("%s%s", this.viewsUrl, "/1/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.PUT,
                null, Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateExistViews() {
        Address address = this.addressRepo.save(ViewsControllerMocks.addressForSave());
        Contact contact = this.contactsRepo.save(ViewsControllerMocks.contactForSave());
        Profile profile = this.profilesRepo.save(ViewsControllerMocks.profileForSave());
        View view = this.viewsRepo.save(ViewsControllerMocks.viewForSave());
        this.accountsRepo.save(ViewsControllerMocks.accountForSave(profile, contact, address, view));
        String url = String.format("%s%s", this.viewsUrl, "/1/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.PUT,
                null, Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
