package com.github.products.controllers.impl;

import com.github.products.controllers.ICommentController;
import com.github.products.dto.CommentDto;
import com.github.products.entity.Comment;
import com.github.products.entity.Product;
import com.github.products.exceptions.BadRequest;
import com.github.products.services.ICommentService;
import com.github.products.services.IProductService;
import com.github.products.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static com.github.products.utils.TransferObj.fromComment;
import static com.github.products.utils.TransferObj.toComment;

@RestController()
@RequiredArgsConstructor
@RequestMapping(path = "/v1/comments")
public class CommentController implements ICommentController {

    private final ICommentService commentService;

    private final IProductService productService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public CommentDto
    save(Long productId, CommentDto payload) {
        if (Objects.isNull(productId) || Objects.isNull(payload)) {
            throw new BadRequest();
        }
        Comment tc = toComment(payload);
        Product product = this.productService.readById(productId);
        Comment comment = this.commentService.create(tc);
        product.addComment(comment);
        this.productService.update(product);
        return fromComment(comment);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public CommentDto findById(Long id) {
        return fromComment(this.commentService.readById(id));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void remove(Long id) {
        this.commentService.remove(id);
    }

}
