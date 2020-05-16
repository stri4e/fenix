package com.github.admins.controllers;

import com.github.admins.dto.CommentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ICommentController {

    @PostMapping(path = "/{productId}")
    ResponseEntity<CommentDto>
    addComment(@PathVariable Long productId,
               @RequestBody CommentDto payload);

    @GetMapping(path = "/{id}")
    ResponseEntity<CommentDto> getComment(@PathVariable Long id);

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Void> remove(@PathVariable Long id);

}
