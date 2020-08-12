package com.github.admins.controllers.impl;

import com.github.admins.controllers.ICommentController;
import com.github.admins.dto.CommentDto;
import com.github.admins.exceptions.NotFound;
import com.github.admins.services.ICommentService;
import com.github.admins.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.admins.utils.TransferObj.fromComment;

@RestController
@RequestMapping(path = "/v1/comment")
@RequiredArgsConstructor
public class CommentController implements ICommentController {

    private final ICommentService commentService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public CommentDto findById(Long id) {
        return fromComment(this.commentService.readById(id)
                .orElseThrow(NotFound::new));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void remove(Long id) {
        this.commentService.delete(id);
    }
}
