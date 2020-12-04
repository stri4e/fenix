package com.github.admins.controllers.impl;

import com.github.admins.dto.AssetDto;
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

public class AssetsControllerBase {

    private final MockServerClient client;

    public AssetsControllerBase() {
        this.client = new MockServerClient("127.0.0.1", 2222);
    }

    public void readByStatus() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.GET.name())
                        .withPath("/v1/fetch/all/on"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withHeader(
                                Header.header(
                                        HttpHeaders.CONTENT_TYPE,
                                        MediaType.APPLICATION_JSON_VALUE
                                )
                        ).withBody(JsonBody.json(AssetsControllerMocks.ASSETS))
        );
    }

    public void readById() {
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
                        ).withBody(JsonBody.json(AssetsControllerMocks.response()))
        );
    }

    public void createAsset() {
        AssetDto request = AssetsControllerMocks.request();
        AssetDto response = AssetsControllerMocks.response();

        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.POST.name())
                        .withPath("/v1/edit")
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

    public void updateAsset() {
        AssetDto request = AssetsControllerMocks.request();

        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.PUT.name())
                        .withPath("/v1/edit")
                        .withBody(JsonBody.json(request)),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
        );
    }

    public void removeAsset() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.DELETE.name())
                        .withPath("/v1/edit/1"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.NO_CONTENT.value())
        );
    }


}
