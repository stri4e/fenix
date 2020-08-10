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

import static com.github.admins.controllers.impl.CategoryControllerMocks.*;

public class CategoryTestBase {

    private final MockServerClient client;

    public CategoryTestBase() {
        this.client = new MockServerClient("127.0.0.1", 2222);
    }

    public void create() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.POST.name())
                        .withPath("/v1/categories/edit")
                        .withBody(JsonBody.json(categoryDtoWithoutId())),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.CREATED.value())
                        .withHeader(Header.header(
                                HttpHeaders.CONTENT_TYPE,
                                MediaType.APPLICATION_JSON_VALUE
                        )).withBody(JsonBody.json(payload()))
        );
    }

    public void readByName() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.GET.name())
                        .withPath("/v1/categories/fetch/Phone"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withHeader(
                                Header.header(
                                        HttpHeaders.CONTENT_TYPE,
                                        MediaType.APPLICATION_JSON_VALUE
                                )
                        ).withBody(JsonBody.json(CategoryControllerMocks.categoryDto()))
        );
    }

    public void readAll() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.GET.name())
                        .withPath("/v1/categories"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withHeader(
                                Header.header(
                                        HttpHeaders.CONTENT_TYPE,
                                        MediaType.APPLICATION_JSON_VALUE
                                )
                        ).withBody(JsonBody.json(CATEGORIES))
        );
    }

    public void update() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.PUT.name())
                        .withBody(JsonBody.json(payload()))
                        .withPath("/v1/categories/edit"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response().withStatusCode(HttpStatus.OK.value())
        );
    }

    public void delete() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.DELETE.name())
                        .withPath("/v1/categories/edit/1"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response().withStatusCode(HttpStatus.CREATED.value())
        );
    }

}
