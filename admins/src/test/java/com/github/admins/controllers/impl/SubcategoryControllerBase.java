package com.github.admins.controllers.impl;

import com.github.admins.dto.SubcategoryDto;
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

public class SubcategoryControllerBase {

    private final MockServerClient client;

    public SubcategoryControllerBase() {
        this.client = new MockServerClient("127.0.0.1", 2222);
    }

    public void createSubcategory() {
        SubcategoryDto request = SubcategoryControllerMocks.request();
        SubcategoryDto response = SubcategoryControllerMocks.response();

        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.POST.name())
                        .withPath("/v1/subcategories/edit/omg")
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
        SubcategoryDto response = SubcategoryControllerMocks.response();
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.GET.name())
                        .withPath("/v1/subcategories/fetch")
                        .withQueryStringParameter("name", "omg"),
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

    public void updateSubcategory() {
        SubcategoryDto request = SubcategoryControllerMocks.request();

        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.PUT.name())
                        .withPath("/v1/subcategories/edit")
                        .withBody(JsonBody.json(request)),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
        );
    }

    public void removeSubcategory() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.DELETE.name())
                        .withPath("/v1/subcategories/edit/1"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.NO_CONTENT.value())
        );
    }

}
