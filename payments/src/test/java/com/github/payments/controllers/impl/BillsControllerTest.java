package com.github.payments.controllers.impl;

import com.github.payments.dto.BillDto;
import com.github.payments.entity.*;
import com.github.payments.payload.Report;
import com.github.payments.repository.*;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
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
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class BillsControllerTest extends BillsControllerBase {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BillRepo billRepo;

    @Autowired
    private PaymentTypesRepo paymentTypesRepo;

    @Autowired
    private AssetsRepo assetsRepo;

    @Autowired
    private AliasRepo aliasRepo;

    @Autowired
    private WhomRepo whomRepo;

    @Autowired
    private WhoRepo whoRepo;

    private String billsUrl;

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
                "%s%d%s", PaymentsConstants.LOCALHOST, port, "/v1/bills"
        );
        this.billsUrl = new URL(url).toString();
    }

    @Test
    public void saveForDef() {
        BillDto payload = BillsControllerMocks.request();
        BillDto exp = BillsControllerMocks.response();
        this.paymentTypesRepo.save(BillsControllerMocks.paymentTypesForSave());
        this.assetsRepo.save(BillsControllerMocks.assetForSave());
        String url = String.format("%s%s", this.billsUrl, "/def");
        ResponseEntity<BillDto> response = this.restTemplate.exchange(
                url, HttpMethod.POST,
                new HttpEntity<>(payload),
                BillDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        BillDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void saveForOther() {
        BillDto payload = BillsControllerMocks.request();
        BillDto exp = BillsControllerMocks.response();
        this.paymentTypesRepo.save(BillsControllerMocks.paymentTypesForSave());
        this.assetsRepo.save(BillsControllerMocks.assetForSave());
        String url = String.format("%s%s", this.billsUrl, "/other");
        ResponseEntity<BillDto> response = this.restTemplate.exchange(
                url, HttpMethod.POST,
                new HttpEntity<>(payload),
                BillDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        BillDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void findById() {
        BillDto exp = BillsControllerMocks.response();
        PaymentTypes paymentTypes = this.paymentTypesRepo.save(BillsControllerMocks.paymentTypesForSave());
        Asset asset = this.assetsRepo.save(BillsControllerMocks.assetForSave());
        Who who = this.whoRepo.save(BillsControllerMocks.whoForSave());
        Whom whom = this.whomRepo.save(BillsControllerMocks.whomForSave());
        this.billRepo.save(BillsControllerMocks.billForSave(asset, paymentTypes, who, whom));
        String url = String.format("%s%s", this.billsUrl, "/fetch?id=1");
        ResponseEntity<BillDto> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null, BillDto.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        BillDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    public void findByStatus() {
        List<BillDto> exp = List.of(BillsControllerMocks.response());
        PaymentTypes paymentTypes = this.paymentTypesRepo.save(BillsControllerMocks.paymentTypesForSave());
        Asset asset = this.assetsRepo.save(BillsControllerMocks.assetForSave());
        Who who = this.whoRepo.save(BillsControllerMocks.whoForSave());
        Whom whom = this.whomRepo.save(BillsControllerMocks.whomForSave());
        this.billRepo.save(BillsControllerMocks.billForSave(asset, paymentTypes, who, whom));
        String url = String.format("%s%s", this.billsUrl, "/fetch?status=on");
        ResponseEntity<List<BillDto>> response = this.restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                }
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<BillDto> act = response.getBody();
        assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

    @Test
    public void updateCrypto() {
        updateOrderAndCurrency();
        PaymentTypes paymentTypes = this.paymentTypesRepo.save(BillsControllerMocks.paymentTypesForSave());
        Asset asset = this.assetsRepo.save(BillsControllerMocks.assetForSave());
        Who who = this.whoRepo.save(BillsControllerMocks.whoForSave());
        Whom whom = this.whomRepo.save(BillsControllerMocks.whomForSave());
        this.billRepo.save(BillsControllerMocks.billForSave(asset, paymentTypes, who, whom));
        String url = String.format(
                "%s%s%s%s%s%s%s", this.billsUrl, "/edit/crypto/",
                BillsControllerMocks.ADDRESS_ETH,
                "/",
                BillsControllerMocks.VALUE_ETH,
                "/",
                BillsControllerMocks.TRANSFER_HASH
        );
        ResponseEntity<Report> response = this.restTemplate.exchange(
                url, HttpMethod.PUT, null, Report.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateMastercard() {
        updateOrderAndMastercard();
        PaymentTypes paymentTypes = this.paymentTypesRepo.save(BillsControllerMocks.paymentTypesForSave());
        Asset asset = this.assetsRepo.save(BillsControllerMocks.assetForSave());
        Who who = this.whoRepo.save(BillsControllerMocks.whoForSave());
        Whom whom = this.whomRepo.save(BillsControllerMocks.whomForSave());
        this.billRepo.save(BillsControllerMocks.billForSave(asset, paymentTypes, who, whom));
        String url = String.format(
                "%s%s%s%s%s%s%s", this.billsUrl, "/edit/mastercard/",
                "1",
                "/",
                BillsControllerMocks.VALUE_ETH,
                "/",
                BillsControllerMocks.TRANSFER_HASH
        );
        ResponseEntity<Report> response = this.restTemplate.exchange(
                url, HttpMethod.PUT, null, Report.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void remove() {
        removeCurrency();
        PaymentTypes paymentTypes = this.paymentTypesRepo.save(BillsControllerMocks.paymentTypesForSave());
        Asset asset = this.assetsRepo.save(BillsControllerMocks.assetForSave());
        Who who = this.whoRepo.save(BillsControllerMocks.whoForSave());
        Whom whom = this.whomRepo.save(BillsControllerMocks.whomForSave());
        this.billRepo.save(BillsControllerMocks.billForSave(asset, paymentTypes, who, whom));
        String url = String.format("%s%s", this.billsUrl, "/1");
        ResponseEntity<Void> response = this.restTemplate.exchange(
                url, HttpMethod.DELETE, null, Void.class
        );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
