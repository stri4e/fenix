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

public class BillsControllerBase {

    private final MockServerClient client;

    public BillsControllerBase() {
        this.client = new MockServerClient("127.0.0.1", 2222);
    }

    public void readByStatus() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.GET.name())
                        .withPath("/v1/bills/fetch")
                        .withQueryStringParameter("status", "on"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withHeader(
                                Header.header(
                                        HttpHeaders.CONTENT_TYPE,
                                        MediaType.APPLICATION_JSON_VALUE
                                )
                        ).withBody(JsonBody.json(BillsControllerMocks.BILLS))
        );
    }

}
