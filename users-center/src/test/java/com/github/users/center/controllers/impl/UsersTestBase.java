package com.github.users.center.controllers.impl;

import org.mockserver.client.MockServerClient;
import org.mockserver.matchers.Times;
import org.mockserver.model.Header;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;

public class UsersTestBase {

    private final MockServerClient client;

    public UsersTestBase() {
        this.client = new MockServerClient("127.0.0.1", 2222);
    }

    public void registrationEmail() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.POST.name())
                        .withPath("/v1/submit/reg"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.CREATED.value())
        );
    }

    public void loginNotification() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.POST.name())
                        .withPath("/v1/login/notification"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.CREATED.value())
        );
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.POST.name())
                        .withPath("/v1/logins/edit"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.CREATED.value())
        );
    }

    public void procResetPass() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.POST.name())
                        .withPath("/v1/reset/pass"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.CREATED.value())
        );
    }

}
