package com.github.accounts.controllers.impl;

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

public class AccountsControllerBase {

    private final MockServerClient client;

    public AccountsControllerBase() {
        this.client = new MockServerClient("127.0.0.1", 2222);
    }

    public void readProductsByIds() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.GET.name())
                        .withPath("/v1/fetch")
                        .withQueryStringParameter("ids",
                                "31", "32", "3", "4", "5",
                                "6", "7", "8", "9", "10",
                                "11", "12", "13", "14", "15",
                                "16", "17", "18", "19", "20",
                                "21", "22", "23", "24", "25",
                                "26", "27", "28", "29", "30"
                        ),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withHeader(
                                Header.header(
                                        HttpHeaders.CONTENT_TYPE,
                                        MediaType.APPLICATION_JSON_VALUE
                                )
                        ).withBody(JsonBody.json(AccountsControllerMocks.VIEWS_PRODUCT))
        );
    }

}
