package com.github.admins.services;

import com.github.admins.dto.CommentDto;
import com.github.admins.services.impl.CommentService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(
        name = "products",
        fallback = CommentService.class,
        contextId = "commentId"
)
public interface ICommentService {

    @GetMapping(
            path = "/v1/comments/fetch/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<CommentDto> readById(@PathVariable Long id);

    @DeleteMapping(
            path = "/v1/comments/edit/{id}"
    )
    void delete(@PathVariable Long id);

}
