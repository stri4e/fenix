package com.github.products.controllers.impl;

import com.github.products.controllers.ICommentController;
import com.github.products.dto.CommentDto;
import com.github.products.services.ICommentService;
import com.github.products.services.IProductService;
import com.github.products.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

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
    save(Long productId, UUID userId, CommentDto payload) {
        return fromComment(
                this.commentService.create(
                        toComment(payload, userId)
                                .addProduct(this.productService.getById(productId))
                )
        );
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
