package com.github.ethereum.controllers.impl;

import com.github.ethereum.EthereumConstant;
import com.github.ethereum.controllers.CustomPageImpl;
import com.github.ethereum.dto.AccountDto;
import com.github.ethereum.entity.Account;
import com.github.ethereum.entity.EntityStatus;
import com.github.ethereum.repository.AccountRepo;
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
import org.springframework.data.domain.Page;
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
public class AccountControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AccountRepo accountRepo;

    private String accountantUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s", EthereumConstant.LOCALHOST, port, "/v1/accounts"
        );
        this.accountantUrl = new URL(url).toString();
    }

    @Test
    public void findByStatus() {
        List<AccountDto> exp = AccountControllerMocks.ACCOUNTS_FOR_EQUALS;
        this.accountRepo.saveAll(AccountControllerMocks.ACCOUNTS_FOR_SAVE);
        String url = String.format("%s%s", this.accountantUrl, "/fetch/off");
        ResponseEntity<CustomPageImpl<AccountDto>> response = this.restTemplate.exchange(
                url,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Page<AccountDto> act = response.getBody();
        Assert.assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

    @Test
    public void save() {
        ResponseEntity<AccountDto> response = this.restTemplate.exchange(
                this.accountantUrl, HttpMethod.POST,
                null,
                AccountDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void findAvailableAddress() {
        Account data = AccountControllerMocks.accountFiveForSave();
        this.accountRepo.save(data);
        String url = String.format("%s%s", this.accountantUrl, "/address");
        ResponseEntity<String> response = this.restTemplate.exchange(
                url,
                HttpMethod.GET, null,
                String.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        String act = response.getBody();
        assertEquals(AccountControllerMocks.EXP_ADDRESS, act);
    }

    @Test
    public void activateAddress() {
        Account data = AccountControllerMocks.accountFiveForSave();
        this.accountRepo.save(data);
        String url = String.format("%s/%s",
                this.accountantUrl,
                AccountControllerMocks.EXP_ADDRESS
        );
        ResponseEntity<String> response = this.restTemplate.exchange(
                url,
                HttpMethod.PUT, null,
                String.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void remove() {
        Account data = AccountControllerMocks.accountFiveForSave();
        data.setStatus(EntityStatus.on);
        this.accountRepo.save(data);
        String url = String.format("%s/%s",
                this.accountantUrl,
                AccountControllerMocks.EXP_ADDRESS
        );
        ResponseEntity<String> response = this.restTemplate.exchange(
                url,
                HttpMethod.DELETE, null,
                String.class
        );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
