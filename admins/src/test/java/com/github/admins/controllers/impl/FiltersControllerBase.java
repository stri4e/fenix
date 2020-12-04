package com.github.admins.controllers.impl;

import com.github.admins.dto.AccountantDto;
import com.github.admins.dto.FilterDto;
import com.github.admins.dto.ProductDto;
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

import static com.github.admins.controllers.impl.ProductControllerMocks.responseProductPayload;

public class FiltersControllerBase {

    private final MockServerClient client;

    public FiltersControllerBase() {
        this.client = new MockServerClient("127.0.0.1", 2222);
    }

    public void createFilter() {
        FilterDto request = FiltersControllerMocks.request();
        FilterDto response = FiltersControllerMocks.response();

        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.POST.name())
                        .withPath("/v1/filters/edit/subcategory1")
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

    public void readById() {
        FilterDto response = FiltersControllerMocks.response();
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.GET.name())
                        .withPath("/v1/filters/fetch/1"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withHeader(
                                Header.header(
                                        HttpHeaders.CONTENT_TYPE,
                                        MediaType.APPLICATION_JSON_VALUE
                                )
                        ).withBody(JsonBody.json(response))
        );
    }

    public void updateFilter() {
        FilterDto request = FiltersControllerMocks.request();

        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.PUT.name())
                        .withPath("/v1/filters/edit")
                        .withBody(JsonBody.json(request)),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
        );
    }

    public void removeFilter() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.DELETE.name())
                        .withPath("/v1/filters/edit/1"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.NO_CONTENT.value())
        );
    }

}
