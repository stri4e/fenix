package com.github.admins.controllers.impl;

import com.github.admins.controllers.ICommentController;
import com.github.admins.dto.CommentDto;
import com.github.admins.payload.Comment;
import com.github.admins.payload.Product;
import com.github.admins.services.ICommentService;
import com.github.admins.services.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.admins.utils.TransferObjects.fromComment;
import static com.github.admins.utils.TransferObjects.toComment;

@RestController
@RequestMapping(path = "/v1/comment")
@RequiredArgsConstructor
public class CommentController implements ICommentController {

    private final ICommentService cs;

    private final IProductService ps;

    @Override
    public ResponseEntity<CommentDto>
    addComment(Long productId, CommentDto payload) {
        Comment tc = toComment(payload);
        Product product = this.ps.readById(productId);
        Comment comment = this.cs.create(tc);
        product.addComment(comment);
        this.ps.update(product);
        return new ResponseEntity<>(
                fromComment(comment), HttpStatus.CREATED
        );
    }

    @Override
    public ResponseEntity<CommentDto> getComment(Long id) {
        Comment comment = this.cs.readById(id);
        return new ResponseEntity<>(
                fromComment(comment), HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Void> remove(Long id) {
        this.cs.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
