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

import static com.github.admins.controllers.impl.OrderDetailControllerMocks.*;

public class OrderDetailTestBase {

    private final MockServerClient client;

    public OrderDetailTestBase() {
        this.client = new MockServerClient("127.0.0.1", 2222);
    }

    public void statusOpen() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.GET.name())
                        .withPath("/v1/fetch/open"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withHeader(Header.header(
                                HttpHeaders.CONTENT_TYPE,
                                MediaType.APPLICATION_JSON_VALUE
                        )).withBody(JsonBody.json(ORDERS_OPEN))
        );

        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.GET.name())
                        .withPath("/v1/fetch")
                        .withQueryStringParameter("ids", "1", "2", "3", "4", "5"),
                Times.exactly(3)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withHeader(
                                Header.header(
                                        HttpHeaders.CONTENT_TYPE,
                                        MediaType.APPLICATION_JSON_VALUE
                                )
                        ).withBody(JsonBody.json(PRODUCTS))
        );

    }

    public void statusClose() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.GET.name())
                        .withPath("/v1/fetch/close"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withHeader(Header.header(
                                HttpHeaders.CONTENT_TYPE,
                                MediaType.APPLICATION_JSON_VALUE
                        )).withBody(JsonBody.json(ORDERS_CLOSE))
        );

        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.GET.name())
                        .withPath("/v1/fetch")
                        .withQueryStringParameter("ids", "1", "2", "3", "4", "5"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withHeader(
                                Header.header(
                                        HttpHeaders.CONTENT_TYPE,
                                        MediaType.APPLICATION_JSON_VALUE
                                )
                        ).withBody(JsonBody.json(PRODUCTS))
        );

    }

    public void statusHandling() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.GET.name())
                        .withPath("/v1/fetch/handling"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withHeader(Header.header(
                                HttpHeaders.CONTENT_TYPE,
                                MediaType.APPLICATION_JSON_VALUE
                        )).withBody(JsonBody.json(ORDERS_HANDLING))
        );

        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.GET.name())
                        .withPath("/v1/fetch")
                        .withQueryStringParameter("ids", "1", "2", "3", "4", "5"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withHeader(
                                Header.header(
                                        HttpHeaders.CONTENT_TYPE,
                                        MediaType.APPLICATION_JSON_VALUE
                                )
                        ).withBody(JsonBody.json(PRODUCTS))
        );

    }

    public void findOrderById() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.GET.name())
                        .withPath("/v1/fetch")
                        .withQueryStringParameter("orderId", "2"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withHeader(Header.header(
                                HttpHeaders.CONTENT_TYPE,
                                MediaType.APPLICATION_JSON_VALUE
                        )).withBody(JsonBody.json(orderDetail()))
        );

        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.GET.name())
                        .withPath("/v1/fetch")
                        .withQueryStringParameter("ids", "1", "2", "3", "4", "5"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withHeader(
                                Header.header(
                                        HttpHeaders.CONTENT_TYPE,
                                        MediaType.APPLICATION_JSON_VALUE
                                )
                        ).withBody(JsonBody.json(PRODUCTS))
        );
    }

    public void update() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.PUT.name())
                        .withPath("/v1/edit")
                        .withBody(JsonBody.json(orderDetail())),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withHeader(Header.header(
                                HttpHeaders.CONTENT_TYPE,
                                MediaType.APPLICATION_JSON_VALUE))
        );
    }

    public void updateStatus() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.PUT.name())
                        .withPath("/v1/edit/1/open"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withHeader(Header.header(
                                HttpHeaders.CONTENT_TYPE,
                                MediaType.APPLICATION_JSON_VALUE))
        );
    }

}
