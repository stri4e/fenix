package com.github.ethereum.controllers.impl;

import com.github.ethereum.EthereumConstant;
import com.github.ethereum.controllers.CustomPageImpl;
import com.github.ethereum.dto.TransactionDto;
import com.github.ethereum.payload.Receipt;
import com.github.ethereum.repository.AccountRepo;
import com.github.ethereum.repository.ContractRepo;
import com.github.ethereum.repository.FeeRepo;
import com.github.ethereum.repository.TransactionRepo;
import jdk.jfr.Description;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockserver.integration.ClientAndServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
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
@FixMethodOrder
public class TransactionControllerTest extends TransactionControllerBase {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private FeeRepo feeRepo;

    @Autowired
    private ContractRepo contractRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    private String transactionsUrl;

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
                "%s%d%s", EthereumConstant.LOCALHOST, port, "/v1/transactions"
        );
        this.transactionsUrl = new URL(url).toString();
    }

    @Test
    @Description(value = "Don't rename this method. It's need for mocks rpc server!")
    public void sendTransaction() {
        sendTransactionRequest();
        TransactionDto exp = TransactionControllerMocks.responseTrx();
        this.contractRepo.save(TransactionControllerMocks.defaultContractForSave());
        this.accountRepo.save(TransactionControllerMocks.accountForSave());
        this.feeRepo.save(TransactionControllerMocks.feeForSave());
        Receipt payload = TransactionControllerMocks.receipt();
        String url = String.format("%s%s", this.transactionsUrl, "/edit/trx");
        ResponseEntity<TransactionDto> response = this.restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(payload),
                TransactionDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        TransactionDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    @Description(value = "Don't rename this method. It's need for mocks rpc server!")
    public void sendContract()  {
        sendContractRequest();
        TransactionDto exp = TransactionControllerMocks.responseContract();
        this.contractRepo.save(TransactionControllerMocks.contractForSave());
        this.accountRepo.save(TransactionControllerMocks.accountForSave());
        this.feeRepo.save(TransactionControllerMocks.feeForSave());
        Receipt payload = TransactionControllerMocks.receipt();
        String url = String.format(
                "%s%s", this.transactionsUrl,
                "/edit/contract/0x65E5BC985b8399B338C3C55ff1e3c048586d50ca");
        ResponseEntity<TransactionDto> response = this.restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(payload),
                TransactionDto.class
        );
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        TransactionDto act = response.getBody();
        assertEquals(exp, act);
    }

    @Test
    @Description(value = "Don't rename this method. It's need for mocks rpc server!")
    public void findAllByStatus() {
        this.contractRepo.save(TransactionControllerMocks.defaultContractForSave());
        List<TransactionDto> exp = TransactionControllerMocks.TRANSACTIONS_FOR_EQUALS;
        this.transactionRepo.saveAll(TransactionControllerMocks.TRANSACTIONS_FOR_SAVE);
        String url = String.format("%s%s", this.transactionsUrl, "/fetch/on");
        ResponseEntity<CustomPageImpl<TransactionDto>> response = this.restTemplate.exchange(
                url,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {}
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Page<TransactionDto> act = response.getBody();
        Assert.assertThat(act, IsIterableContainingInAnyOrder.containsInAnyOrder(exp.toArray()));
    }

}
