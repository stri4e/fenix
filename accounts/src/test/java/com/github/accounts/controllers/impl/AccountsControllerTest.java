package com.github.accounts.controllers.impl;

import com.github.accounts.dto.AccountDto;
import com.github.accounts.entity.Address;
import com.github.accounts.entity.Contact;
import com.github.accounts.entity.Profile;
import com.github.accounts.entity.View;
import com.github.accounts.repository.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.integration.ClientAndServer;
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
import java.util.List;

import static com.github.accounts.AccountsConstant.LOCALHOST;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class AccountsControllerTest extends AccountsControllerBase {

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

    private String accountUrl;

    private static ClientAndServer mockServer;

    @BeforeClass
    public static void startServer() {
        mockServer = ClientAndServer.startClientAndServer(2222);
    }

    @AfterClass
    public static void downServer() {
        mockServer.stop();
    }

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s", LOCALHOST, port, "/v1"
        );
        this.accountUrl = new URL(url).toString();
    }

    @Test
    public void findAccount() {
        readProductsByIds();
        AccountDto exp = AccountsControllerMocks.response();
        Address address = this.addressRepo.save(AccountsControllerMocks.addressForSave());
        Contact contact = this.contactsRepo.save(AccountsControllerMocks.contactForSave());
        Profile profile = this.profilesRepo.save(AccountsControllerMocks.profileForSave());
        List<View> views = this.viewsRepo.saveAll(AccountsControllerMocks.VIEWS_FOR_SAVE);
        this.accountsRepo.save(AccountsControllerMocks.accountForSave(profile, contact, address, views));
        ResponseEntity<AccountDto> response = this.restTemplate.exchange(
                this.accountUrl, HttpMethod.GET,
                null, AccountDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        AccountDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void save() {
        AccountDto exp = AccountsControllerMocks.response2();
        AccountDto payload = AccountsControllerMocks.request();
        this.addressRepo.save(AccountsControllerMocks.addressForSave());
        this.contactsRepo.save(AccountsControllerMocks.contactForSave());
        this.profilesRepo.save(AccountsControllerMocks.profileForSave());
        ResponseEntity<AccountDto> response = this.restTemplate.exchange(
                this.accountUrl, HttpMethod.POST,
                new HttpEntity<>(payload), AccountDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        AccountDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void saveDefault() {
        AccountDto exp = AccountsControllerMocks.response3();
        String url = String.format("%s%s", this.accountUrl, "/default");
        ResponseEntity<AccountDto> response = this.restTemplate.exchange(
                url, HttpMethod.POST,
                null, AccountDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        AccountDto act = response.getBody();
        assertEquals(exp, act);
    }

}
