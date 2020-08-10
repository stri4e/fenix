package com.github.admins.controllers.impl;

import com.github.admins.payload.Comment;
import com.github.admins.payload.Product;
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

import static com.github.admins.controllers.impl.CommentControllerMocks.requestPayload;
import static com.github.admins.controllers.impl.CommentControllerMocks.responsePayload;

public class CommentTestBase {

    private final MockServerClient client;

    public CommentTestBase() {
        this.client = new MockServerClient("127.0.0.1", 2222);
    }

    public void create() {
        Comment requestPayload = requestPayload();
        Comment responsePayload = responsePayload();
        Product product = CommentControllerMocks.product();

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
                        .withPath("/v1/comments")
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

        product.addComment(responsePayload);
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
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.GET.name())
                        .withPath("/v1/comments/fetch/1"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withHeader(
                                Header.header(
                                        HttpHeaders.CONTENT_TYPE,
                                        MediaType.APPLICATION_JSON_VALUE
                                )
                        ).withBody(JsonBody.json(responsePayload()))
        );
    }

    public void delete() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.DELETE.name())
                        .withPath("/v1/comments/edit/1"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response().withStatusCode(
                        HttpStatus.NO_CONTENT.value()
                )
        );
    }

}
