package com.github.products.controllers.impl;

import com.github.products.controllers.ICommentController;
import com.github.products.dto.CommentDto;
import com.github.products.entity.Comment;
import com.github.products.exceptions.BadRequest;
import com.github.products.exceptions.TypeMessage;
import com.github.products.services.ICommentService;
import com.github.products.utils.Logging;
import com.github.products.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CommentDto>
    addComment(Long productId, CommentDto payload) {
        if (Objects.isNull(productId) || Objects.isNull(payload)) {
            throw new BadRequest(TypeMessage.invalidPayload);
        }
        Comment comment = TransferObj.transferCommentDto(payload);
        Comment result = this.commentService.create(comment);
        return new ResponseEntity<>(
                TransferObj.transferComment(result), HttpStatus.CREATED
        );
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
