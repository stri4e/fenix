package com.github.products.controllers;

import com.github.products.dto.CommentDto;
import com.github.products.entity.Comment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ICommentController {

    @PostMapping(
            path = "/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<CommentDto> addComment(
            @PathVariable Long productId,
            @RequestBody CommentDto payload
    );

    @GetMapping(
            path = "/info/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Comment readById(@PathVariable Long id);

    @DeleteMapping(
            path = "/edit/{id}"
    )
    void remove(@PathVariable Long id);

}
