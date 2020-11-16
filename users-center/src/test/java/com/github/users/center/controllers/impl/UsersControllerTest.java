package com.github.users.center.controllers.impl;

import com.github.users.center.controllers.ControllersMocks;
import com.github.users.center.dto.ForgotPassDto;
import com.github.users.center.dto.UserAuthDto;
import com.github.users.center.dto.UserRegDto;
import com.github.users.center.entity.ConfirmToken;
import com.github.users.center.entity.PassResetToken;
import com.github.users.center.entity.User;
import com.github.users.center.payload.RenderTemplate;
import com.github.users.center.utils.ConfirmReport;
import com.github.users.center.payload.JwtAuthResponse;
import com.github.users.center.repository.ConfirmTokenRepo;
import com.github.users.center.repository.PassResetRepo;
import com.github.users.center.repository.UserRepo;
import com.github.users.center.services.IUserService;
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
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class UsersControllerTest extends UsersTestBase {

    private static final String LOCALHOST = "http://localhost:";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private IUserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PassResetRepo passResetRepo;

    @Autowired
    private ConfirmTokenRepo confirmTokenRepo;

    private URL submitRegUrl;

    private URL submitAuthUrl;

    private URL processForgotPassUrl;

    private URL submitResetPassUrl;

    private String submitRegHeaderName;

    private String submitRegHeader;

    private MultiValueMap<String, String> authHeaders;

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
    public void setUp() throws MalformedURLException {
        this.submitRegUrl = new URL(String.format("%s%d%s", LOCALHOST, port, "/v1/reg"));
        this.submitAuthUrl = new URL(String.format("%s%d%s", LOCALHOST, port, "/v1/auth"));
        this.processForgotPassUrl = new URL(String.format("%s%d%s", LOCALHOST, port, "/v1/forgot-pass"));
        this.submitResetPassUrl = new URL(String.format("%s%d%s", LOCALHOST, port, "/v1/edit/reset-pass"));
        this.submitRegHeaderName = "Origin";
        this.submitRegHeader = String.format("%s%d", LOCALHOST, port);
        this.authHeaders = new LinkedMultiValueMap<>();
        this.authHeaders.add("X-Forwarded-For", "http://localhost:8080");
        this.authHeaders.add("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0");
        this.authHeaders.add("Origin", "http://localhost:8080");
        this.authHeaders.add("X-Forwarded-Prefix", "/users");
    }

    //=================================================
    //============== SUBMIT REGISTRATION ==============
    //=================================================

    @Test
    public void submitReg() {
        registrationEmail();
        User expUser = ControllersMocks.userExp();
        UserRegDto payload = ControllersMocks.userRegDto();
        HttpHeaders headers = new HttpHeaders(this.authHeaders);
        HttpEntity<UserRegDto> entity = new HttpEntity<>(payload, headers);
        ResponseEntity<Void> result = this.restTemplate
                .postForEntity(this.submitRegUrl.toString(), entity, Void.class);
        assertEquals(HttpStatus.CREATED.value(), result.getStatusCode().value());
        User actUser = this.userService.readByEmail(ControllersMocks.EMAIL);
        assertNotNull(actUser);
        submitEq(expUser, actUser);
    }

    private void submitEq(User exp, User act) {
        assertEquals(exp.getFName(), act.getFName());
        assertEquals(exp.getLName(), act.getLName());
        assertEquals(exp.getLogin(), act.getLogin());
        assertEquals(exp.getEmail(), act.getEmail());
        assertTrue(this.encoder.matches(exp.getPass(), act.getPass()));
    }

    @Test
    public void submitRegConflict() {
        loginNotification();
        User user = ControllersMocks.user();
        UserRegDto payload = ControllersMocks.userRegDto();
        this.userService.create(user);
        HttpHeaders headers = new HttpHeaders(this.authHeaders);
        HttpEntity<UserRegDto> entity = new HttpEntity<>(payload, headers);
        ResponseEntity<Void> result = this.restTemplate
                .postForEntity(this.submitRegUrl.toString(), entity, Void.class);
        assertEquals(HttpStatus.CONFLICT.value(), result.getStatusCode().value());
    }

    @Test
    public void submitRegPayloadFirstNameNull() {
        UserRegDto payload = ControllersMocks.userRegDtoNotValid();
        HttpHeaders headers = new HttpHeaders();
        headers.set(this.submitRegHeaderName, this.submitRegHeader);
        HttpEntity<UserRegDto> entity = new HttpEntity<>(payload, headers);
        ResponseEntity<Void> result = this.restTemplate
                .postForEntity(this.submitRegUrl.toString(), entity, Void.class);
        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatusCode().value());
    }

    //=================================================
    //============== CONFIRM ACCOUNT ==================
    //=================================================

    @Test
    public void confirmAccount() throws MalformedURLException, URISyntaxException {
        registrationEmail();
        UserRegDto payload = ControllersMocks.userRegDto();
        HttpHeaders headers = new HttpHeaders(this.authHeaders);
        HttpEntity<UserRegDto> entity = new HttpEntity<>(payload, headers);
        ResponseEntity<Void> result = this.restTemplate
                .postForEntity(this.submitRegUrl.toString(), entity, Void.class);
        assertEquals(HttpStatus.CREATED.value(), result.getStatusCode().value());
        User user = this.userService.readByEmail(ControllersMocks.EMAIL);
        ConfirmToken confirmToken = this.confirmTokenRepo.findByUserId(user.getId())
                .orElse(null);
        assertNotNull(confirmToken);
        URL confirmAccountUrl = new URL(String.format(
                "%s%d%s", LOCALHOST, port, "/v1/edit/confirm-account/"
                        + confirmToken.getToken()
        ));
        ResponseEntity<RenderTemplate> resultConfirm = this.restTemplate
                .postForEntity(confirmAccountUrl.toURI(), null, RenderTemplate.class);
        assertEquals(HttpStatus.OK, resultConfirm.getStatusCode());
        RenderTemplate act = resultConfirm.getBody();
        assertEquals(RenderTemplate.success(), act);
    }

    @Test
    public void confirmAccountTokenEmpty() throws MalformedURLException, URISyntaxException {
        registrationEmail();
        UserRegDto payload = ControllersMocks.userRegDto();
        HttpHeaders headers = new HttpHeaders(this.authHeaders);
        HttpEntity<UserRegDto> entity = new HttpEntity<>(payload, headers);
        ResponseEntity<Void> result = this.restTemplate
                .postForEntity(this.submitRegUrl.toString(), entity, Void.class);
        assertEquals(HttpStatus.CREATED.value(), result.getStatusCode().value());
        User user = this.userService.readByEmail(ControllersMocks.EMAIL);
        ConfirmToken confirmToken = this.confirmTokenRepo.findByUserId(user.getId())
                .orElse(null);
        assertNotNull(confirmToken);
        URL confirmAccountUrl = new URL(String.format(
                "%s%d%s", LOCALHOST, port, "/v1/edit/confirm-account/"
        ));
        ResponseEntity<RenderTemplate> resultConfirm = this.restTemplate
                .postForEntity(confirmAccountUrl.toURI(), null, RenderTemplate.class);
        assertEquals(HttpStatus.NOT_FOUND, resultConfirm.getStatusCode());
    }

    //=================================================
    //============== SUBMIT AUTH ======================
    //=================================================

    @Test
    public void submitAuth() {
        loginNotification();
        UserAuthDto payload = ControllersMocks.userAuthDto();
        User userForAuth = ControllersMocks.userForAuth();
        this.userService.create(userForAuth);
        HttpHeaders headers = new HttpHeaders(this.authHeaders);
        HttpEntity<UserAuthDto> entity = new HttpEntity<>(payload, headers);
        ResponseEntity<JwtAuthResponse> result = this.restTemplate
                .postForEntity(this.submitAuthUrl.toString(), entity, JwtAuthResponse.class);
        JwtAuthResponse act = result.getBody();
        assertNotNull(act);
    }

    @Test
    public void submitAuthUnauthorized() {
        loginNotification();
        UserAuthDto payload = ControllersMocks.userAuthDtoNotValid();
        User userForAuth = ControllersMocks.userForAuth();
        this.userService.create(userForAuth);
        HttpHeaders headers = new HttpHeaders(this.authHeaders);
        HttpEntity<UserAuthDto> entity = new HttpEntity<>(payload, headers);
        ResponseEntity<JwtAuthResponse> result = this.restTemplate
                .postForEntity(this.submitAuthUrl.toString(), entity, JwtAuthResponse.class);
        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }

    //=================================================
    //============== PROCESS FORGOT PASS ==============
    //=================================================

    @Test
    public void processForgotPass() {
        procResetPass();
        ForgotPassDto payload = ControllersMocks.forgotPassDto();
        User userForAuth = ControllersMocks.userForAuth();
        this.userService.create(userForAuth);
        HttpHeaders headers = new HttpHeaders(this.authHeaders);
        HttpEntity<ForgotPassDto> entity = new HttpEntity<>(payload, headers);
        ResponseEntity<Void> result = this.restTemplate
                .postForEntity(this.processForgotPassUrl.toString(), entity, Void.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    //=================================================
    //================= RESET PASSWORD ================
    //=================================================

    @Test
    public void resetPass() {
        User user = ControllersMocks.user();
        PassResetToken pr = ControllersMocks.passResetToken();
        pr.setExpiryDate(10);
        pr.setNewPass("new_password");
        User u = this.userRepo.save(user);
        pr.setUser(u);
        PassResetToken passReset = this.passResetRepo.save(pr);
        String url = String.format(
                "%s%s", this.submitResetPassUrl, "/" + passReset.getToken()
        );
        ResponseEntity<RenderTemplate> response = this.restTemplate.exchange(
                url, HttpMethod.POST, null, RenderTemplate.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(RenderTemplate.success(), response.getBody());
    }

    @Test
    public void resetPassPreConditionFailed() {
        User user = ControllersMocks.user();
        PassResetToken pr = ControllersMocks.passResetToken();
        pr.setExpiryDate(1);
        pr.setExpireTime(new Date(1597128681L));
        User u = this.userRepo.save(user);
        pr.setUser(u);
        PassResetToken passReset = this.passResetRepo.save(pr);
        String url = String.format(
                "%s%s", this.submitResetPassUrl, "/" + passReset.getToken()
        );
        ResponseEntity<RenderTemplate> response = this.restTemplate.exchange(
                url, HttpMethod.POST, null, RenderTemplate.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(RenderTemplate.error("Token is expired."), response.getBody());
    }

}
