package com.github.managers.controllers;

import com.github.managers.dto.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface ICommentController {

    @GetMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    CommentDto findById(@PathVariable Long id);

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void remove(@PathVariable Long id);

}
