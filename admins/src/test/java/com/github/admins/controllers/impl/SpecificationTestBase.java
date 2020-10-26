package com.github.admins.controllers.impl;

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
        Specification requestPayload = SpecificationControllerMocks.requestPayload();
        Specification responsePayload = SpecificationControllerMocks.responsePayload();
        Product product = SpecificationControllerMocks.product();

        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.GET.name())
                        .withPath("/v1/fetch/1"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withHeader(
                                Header.header(
                                        HttpHeaders.CONTENT_TYPE,
                                        MediaType.APPLICATION_JSON_VALUE
                                )
                        ).withBody(JsonBody.json(product))
        );

        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.POST.name())
                        .withPath("/v1/specification/edit")
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

        product.addSpecification(responsePayload);
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.PUT.name())
                        .withBody(JsonBody.json(product))
                        .withPath("/v1/edit"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response().withStatusCode(HttpStatus.OK.value())
        );

    }

    public void readById() {
        Specification responsePayload = SpecificationControllerMocks.responsePayload();
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
        Specification responsePayload = SpecificationControllerMocks.responsePayload();
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
