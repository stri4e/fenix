package com.github.managers.controllers.impl;

import com.github.managers.controllers.ICommentController;
import com.github.managers.dto.CommentDto;
import com.github.managers.exceptions.NotFound;
import com.github.managers.services.ICommentService;
import com.github.managers.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/comment")
@RequiredArgsConstructor
public class CommentController implements ICommentController {

    private final ICommentService commentService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public CommentDto findById(Long id) {
        return this.commentService.readById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void remove(Long id) {
        this.commentService.delete(id);
    }
}
