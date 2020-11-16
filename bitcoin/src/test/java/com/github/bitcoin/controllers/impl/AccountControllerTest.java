package com.github.bitcoin.controllers.impl;

import com.github.bitcoin.CustomPageImpl;
import com.github.bitcoin.controllers.BitcoinConstant;
import com.github.bitcoin.dto.AccountDto;
import com.github.bitcoin.dto.AddressDto;
import com.github.bitcoin.entity.Account;
import com.github.bitcoin.entity.Address;
import com.github.bitcoin.entity.EntityStatus;
import com.github.bitcoin.repository.AccountRepo;
import com.github.bitcoin.repository.AddressRepo;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Autowired
    private AddressRepo addressRepo;

    private String accountUrl;

    @Before
    public void setUp() throws Exception {
        String url = String.format(
                "%s%d%s", BitcoinConstant.LOCALHOST, port, "/v1/accounts"
        );
        this.accountUrl = new URL(url).toString();
    }

    @Test
    public void findByStatus() {
        Account accountData = AccountControllerMocks.account();
        Account account = this.accountRepo.save(accountData);
        List<Address> addressesData = AccountControllerMocks.addresses(account);
        List<Address> addresses = this.addressRepo.saveAll(addressesData);
        account.setAddresses(new HashSet<>(addresses));
        this.accountRepo.save(account);
        String url = String.format("%s%s", this.accountUrl, "/fetch/on");
        ResponseEntity<CustomPageImpl<AccountDto>> response = this.restTemplate.exchange(
                url,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Page<AccountDto> act = response.getBody();
        List<AddressDto> actAddreses = act.stream()
                .flatMap(a -> a.getAddresses().stream())
                .collect(Collectors.toList());
        Assert.assertThat(
                AccountControllerMocks.ADDRESSES_FOR_EQUALS,
                IsIterableContainingInAnyOrder.containsInAnyOrder(actAddreses.toArray())
        );
    }

    @Test
    public void save() {
        ResponseEntity<AccountDto> response = this.restTemplate.exchange(
                this.accountUrl, HttpMethod.POST,
                null,
                AccountDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Account account = this.accountRepo.findByUserId(AccountControllerMocks.userId)
                .orElseThrow();
        assertNotNull(account);
        assertNotNull(account.getMnemonic());
        assertNotNull(account.getPrivateKey());
        assertNotNull(account.getPublicKey());
        assertNotNull(account.getChainCode());
    }

    @Test
    public void findAvailableAddress() {
        Account accountData = AccountControllerMocks.account();
        Account account = this.accountRepo.save(accountData);
        List<Address> addressesData = AccountControllerMocks.addresses(account);
        List<Address> addresses = this.addressRepo.saveAll(addressesData);
        account.setAddresses(new HashSet<>(addresses));
        this.accountRepo.save(account);
        String url = String.format("%s%s", this.accountUrl, "/address");
        ResponseEntity<String> response = this.restTemplate.exchange(
                url,
                HttpMethod.GET, null,
                String.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        String act = response.getBody();
        assertNotNull(act);
    }

    @Test
    public void activateAddress() {
        Account accountData = AccountControllerMocks.account();
        Account account = this.accountRepo.save(accountData);
        List<Address> addressesData = AccountControllerMocks.addresses(account);
        List<Address> addresses = this.addressRepo.saveAll(addressesData);
        account.setAddresses(new HashSet<>(addresses));
        this.accountRepo.save(account);
        String url = String.format("%s/%s", this.accountUrl, AccountControllerMocks.ADDRESS_FOR_EQUALS);
        ResponseEntity<String> response = this.restTemplate.exchange(
                url,
                HttpMethod.PUT, null,
                String.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void remove() {
        Account accountData = AccountControllerMocks.account();
        Account account = this.accountRepo.save(accountData);
        Address addressesData = AccountControllerMocks.addressForSave(account);
        addressesData.setStatus(EntityStatus.on);
        Address address = this.addressRepo.save(addressesData);
        account.setAddresses(Set.of(address));
        this.accountRepo.save(account);
        String url = String.format("%s/%s", this.accountUrl, AccountControllerMocks.ADDRESS_FOR_EQUALS);
        ResponseEntity<String> response = this.restTemplate.exchange(
                url,
                HttpMethod.DELETE, null,
                String.class
        );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
