package com.github.accounts.controllers.impl;

import com.github.accounts.dto.ContactDto;
import com.github.accounts.entity.Contact;
import com.github.accounts.repository.ContactsRepo;
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
public class ContactControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ContactsRepo contactsRepo;

    private String contactsUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s", LOCALHOST, port, "/v1/contacts"
        );
        this.contactsUrl = new URL(url).toString();
    }

    @Test
    public void findContact() {
        ContactDto exp = ContactControllerMocks.response();
        Contact contact = ContactControllerMocks.contactForSave();
        this.contactsRepo.save(contact);
        String url = String.format("%s%s", this.contactsUrl, "/1");
        ResponseEntity<ContactDto> response = this.restTemplate.exchange(
                url, HttpMethod.GET,
                null, ContactDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ContactDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void save() {
        ContactDto exp = ContactControllerMocks.response();
        ContactDto payload = ContactControllerMocks.request();
        ResponseEntity<ContactDto> response = this.restTemplate.exchange(
                this.contactsUrl, HttpMethod.POST,
                new HttpEntity<>(payload), ContactDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        ContactDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void update() {
        ContactDto payload = ContactControllerMocks.requestForUpdate();
        this.contactsRepo.save(ContactControllerMocks.contactForSave());
        ResponseEntity<Void> response = this.restTemplate.exchange(
                this.contactsUrl, HttpMethod.PUT,
                new HttpEntity<>(payload), Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
