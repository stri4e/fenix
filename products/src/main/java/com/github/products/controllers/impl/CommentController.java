package com.github.products.controllers.impl;

import com.github.products.controllers.ICommentController;
import com.github.products.dto.CommentDto;
import com.github.products.entity.Comment;
import com.github.products.exceptions.BadRequest;
import com.github.products.services.ICommentService;
import com.github.products.utils.Logging;
import com.github.products.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController()
@RequiredArgsConstructor
@RequestMapping(path = "/v1/comments/")
public class CommentController implements ICommentController {

    private final ICommentService commentService;

    @Override
    @Logging(isTime = true, isReturn = false)
    public CommentDto
    addComment(Long productId, CommentDto payload) {
        if (Objects.isNull(productId) || Objects.isNull(payload)) {
            throw new BadRequest();
        }
        var comment = TransferObj.toComment(payload);
        return TransferObj.fromComment(this.commentService.create(comment));
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public Comment readById(Long id) {
        return this.commentService.readById(id);
    }

    @Override
    @Logging(isTime = true, isReturn = false)
    public void remove(Long id) {
        this.commentService.remove(id);
    }

}
