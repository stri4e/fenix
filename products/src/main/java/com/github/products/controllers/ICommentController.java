package com.github.products.controllers;

import com.github.products.dto.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

public interface ICommentController {

    @PostMapping(
            path = "/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    CommentDto save(
            @PathVariable Long productId,
            @RequestAttribute(name = "userId") UUID userId,
            @Valid @RequestBody CommentDto payload
    );

    @GetMapping(
            path = "/fetch/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    CommentDto findById(@PathVariable Long id);

    @DeleteMapping(
            path = "/edit/{id}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void remove(@PathVariable(name = "id") Long id);

}
