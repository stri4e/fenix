package com.github.products.controllers.impl;

import com.products.service.controllers.ICommentController;
import com.products.service.dto.CommentDto;
import com.products.service.entity.Comment;
import com.products.service.exceptions.BadRequest;
import com.products.service.service.ICommentService;
import com.products.service.utils.TransferObj;
import com.products.service.utils.TypeMessage;
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

}
