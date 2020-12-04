package com.github.admins.controllers.impl;

import com.github.admins.dto.BrandDto;
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

public class BrandsControllerBase {

    private final MockServerClient client;

    public BrandsControllerBase() {
        this.client = new MockServerClient("127.0.0.1", 2222);
    }

    public void createBrand() {
        BrandDto request = BrandsControllerMocks.request();
        BrandDto response = BrandsControllerMocks.response();

        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.POST.name())
                        .withPath("/v1/brands/edit")
                        .withBody(JsonBody.json(request)),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.CREATED.value())
                        .withHeader(Header.header(
                                HttpHeaders.CONTENT_TYPE,
                                MediaType.APPLICATION_JSON_VALUE
                        )).withBody(JsonBody.json(response))
        );
    }

    public void readByName() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.GET.name())
                        .withPath("/v1/brands/fetch/Apple"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withHeader(
                                Header.header(
                                        HttpHeaders.CONTENT_TYPE,
                                        MediaType.APPLICATION_JSON_VALUE
                                )
                        ).withBody(JsonBody.json(BrandsControllerMocks.response()))
        );
    }

    public void updateBrand() {
        BrandDto request = BrandsControllerMocks.request();
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.PUT.name())
                        .withPath("/v1/brands/edit")
                        .withBody(JsonBody.json(request)),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
        );
    }

    public void removeBrand() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.DELETE.name())
                        .withPath("/v1/brands/edit/1"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.NO_CONTENT.value())
        );
    }

}
