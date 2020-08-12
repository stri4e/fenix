package com.github.admins.controllers;

import com.github.admins.dto.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

public interface ICommentController {

    @GetMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    CommentDto findById(@PathVariable Long id);

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void remove(@PathVariable Long id);

}
