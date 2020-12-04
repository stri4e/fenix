package com.github.admins.controllers.impl;

import com.github.admins.dto.SpecificationDto;
import org.mockserver.client.MockServerClient;
import org.mockserver.matchers.Times;
import org.mockserver.model.Header;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.JsonBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;

public class SpecificationTestBase {

    private final MockServerClient client;

    public SpecificationTestBase() {
        this.client = new MockServerClient("127.0.0.1", 2222);
    }

    public void create() {
        SpecificationDto requestPayload = SpecificationControllerMocks.requestPayload();
        SpecificationDto responsePayload = SpecificationControllerMocks.responsePayload();

        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.POST.name())
                        .withPath("/v1/specification/edit/1")
                        .withBody(JsonBody.json(requestPayload)),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.CREATED.value())
                        .withHeader(Header.header(
                                HttpHeaders.CONTENT_TYPE,
                                MediaType.APPLICATION_JSON_VALUE
                        )).withBody(JsonBody.json(responsePayload))
        );
    }

    public void readById() {
        SpecificationDto responsePayload = SpecificationControllerMocks.responsePayload();
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.GET.name())
                        .withPath("/v1/specification/fetch/1"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withHeader(
                                Header.header(
                                        HttpHeaders.CONTENT_TYPE,
                                        MediaType.APPLICATION_JSON_VALUE
                                )
                        ).withBody(JsonBody.json(responsePayload))
        );
    }

    public void update() {
        SpecificationDto responsePayload = SpecificationControllerMocks.responsePayload();
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.PUT.name())
                        .withBody(JsonBody.json(responsePayload))
                        .withPath("/v1/specification/edit"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response().withStatusCode(HttpStatus.OK.value())
        );
    }

}
