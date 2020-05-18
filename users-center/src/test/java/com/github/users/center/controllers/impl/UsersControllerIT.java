package com.github.users.center.controllers.impl;

import com.github.users.center.controllers.ControllersMocks;
import com.github.users.center.dto.ForgotPassDto;
import com.github.users.center.dto.UserAuthDto;
import com.github.users.center.dto.UserRegDto;
import com.github.users.center.entity.ConfirmToken;
import com.github.users.center.entity.User;
import com.github.users.center.payload.JwtAuthResponse;
import com.github.users.center.repository.ConfirmTokenRepo;
import com.github.users.center.services.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(
        properties = "eureka.client.enabled=false",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(profiles = "test")
public class UsersControllerIT {

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
    private ConfirmTokenRepo confirmTokenRepo;

    private URL submitRegUrl;

    private URL submitAuthUrl;
    private URL processForgotPassUrl;

    private String submitRegHeaderName;

    private String submitRegHeader;

    @Before
    public void setUp() throws MalformedURLException {
        this.submitRegUrl = new URL(String.format("%s%d%s", LOCALHOST, port, "/v1/reg"));
        this.submitAuthUrl = new URL(String.format("%s%d%s", LOCALHOST, port, "/v1/auth"));
        this.processForgotPassUrl = new URL(String.format("%s%d%s", LOCALHOST, port, "/v1/forgot-pass"));
        this.submitRegHeaderName = "Client-Address";
        this.submitRegHeader = String.format("%s%d", LOCALHOST, port);
    }

    //=================================================
    //============== SUBMIT REGISTRATION ==============
    //=================================================

    @Test
    public void submitReg() {
        User expUser = ControllersMocks.userExp();
        UserRegDto payload = ControllersMocks.userRegDto();
        HttpHeaders headers = new HttpHeaders();
        headers.set(this.submitRegHeaderName, this.submitRegHeader);
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
        User user = ControllersMocks.user();
        UserRegDto payload = ControllersMocks.userRegDto();
        this.userService.create(user);
        HttpHeaders headers = new HttpHeaders();
        headers.set(this.submitRegHeaderName, this.submitRegHeader);
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
    public void confirmAccount() throws MalformedURLException {
        UserRegDto payload = ControllersMocks.userRegDto();
        HttpHeaders headers = new HttpHeaders();
        headers.set(this.submitRegHeaderName, this.submitRegHeader);
        HttpEntity<UserRegDto> entity = new HttpEntity<>(payload, headers);
        ResponseEntity<Void> result = this.restTemplate
                .postForEntity(this.submitRegUrl.toString(), entity, Void.class);
        assertEquals(HttpStatus.CREATED.value(), result.getStatusCode().value());
        User user = this.userService.readByEmail(ControllersMocks.EMAIL);
        ConfirmToken confirmToken = this.confirmTokenRepo.findByUserId(user.getId())
                .orElse(null);
        assertNotNull(confirmToken);
        URL confirmAccountUrl = new URL(String.format(
                "%s%d%s", LOCALHOST, port, "/v1/confirm-account?token="
                        + confirmToken.getToken()
        ));
        ResponseEntity<Void> resultConfirm = this.restTemplate
                .getForEntity(confirmAccountUrl.toString(), Void.class);
        assertEquals(HttpStatus.SEE_OTHER, resultConfirm.getStatusCode());
    }

    @Test
    public void confirmAccountTokenEmpty() throws MalformedURLException {
        UserRegDto payload = ControllersMocks.userRegDto();
        HttpHeaders headers = new HttpHeaders();
        headers.set(this.submitRegHeaderName, this.submitRegHeader);
        HttpEntity<UserRegDto> entity = new HttpEntity<>(payload, headers);
        ResponseEntity<Void> result = this.restTemplate
                .postForEntity(this.submitRegUrl.toString(), entity, Void.class);
        assertEquals(HttpStatus.CREATED.value(), result.getStatusCode().value());
        User user = this.userService.readByEmail(ControllersMocks.EMAIL);
        ConfirmToken confirmToken = this.confirmTokenRepo.findByUserId(user.getId())
                .orElse(null);
        assertNotNull(confirmToken);
        URL confirmAccountUrl = new URL(String.format(
                "%s%d%s", LOCALHOST, port, "/v1/confirm-account?token="
        ));
        ResponseEntity<Void> resultConfirm = this.restTemplate
                .getForEntity(confirmAccountUrl.toString(), Void.class);
        assertEquals(HttpStatus.BAD_REQUEST, resultConfirm.getStatusCode());
    }

    //=================================================
    //============== SUBMIT AUTH ======================
    //=================================================

    @Test
    public void submitAuth() {
        UserAuthDto payload = ControllersMocks.userAuthDto();
        User userForAuth = ControllersMocks.userForAuth();
        this.userService.create(userForAuth);
        ResponseEntity<JwtAuthResponse> result = this.restTemplate
                .postForEntity(this.submitAuthUrl.toString(), payload, JwtAuthResponse.class);
        JwtAuthResponse act = result.getBody();
        assertNotNull(act);
    }

    @Test
    public void submitAuthUnauthorized() {
        UserAuthDto payload = ControllersMocks.userAuthDtoNotValid();
        User userForAuth = ControllersMocks.userForAuth();
        this.userService.create(userForAuth);
        ResponseEntity<JwtAuthResponse> result = this.restTemplate
                .postForEntity(this.submitAuthUrl.toString(), payload, JwtAuthResponse.class);
        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }

    //=================================================
    //============== PROCESS FORGOT PASS ==============
    //=================================================

    @Test
    public void processForgotPass() {
        ForgotPassDto payload = ControllersMocks.forgotPassDto();
        User userForAuth = ControllersMocks.userForAuth();
        this.userService.create(userForAuth);
        ResponseEntity<Void> result = this.restTemplate
                .postForEntity(this.processForgotPassUrl.toString(), payload, Void.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}