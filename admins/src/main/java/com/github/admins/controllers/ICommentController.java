package com.github.admins.controllers;

import com.github.admins.dto.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

public interface ICommentController {

    @PostMapping(path = "/{productId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    CommentDto save(@PathVariable Long productId,
                    @RequestBody CommentDto payload
    );

    @GetMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    CommentDto findById(@PathVariable Long id);

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void remove(@PathVariable Long id);

}
