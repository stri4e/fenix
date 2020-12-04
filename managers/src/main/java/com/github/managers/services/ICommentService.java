package com.github.managers.services;

import com.github.managers.dto.CommentDto;
import com.github.managers.services.impl.CommentService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
