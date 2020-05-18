package com.github.products.services.impl;

import com.github.products.entity.Comment;
import com.github.products.exceptions.BadRequest;
import com.github.products.exceptions.NotFound;
import com.github.products.exceptions.TypeMessage;
import com.github.products.repository.CommentRepo;
import com.github.products.services.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "comment")
public class CommentService implements ICommentService {

    private final CommentRepo commentRepo;

    @Override
    @Caching(
            put = @CachePut(value = "comment", key = "#c.id")
    )
    public Comment create(Comment c) {
        if (Objects.isNull(c)) {
            throw new BadRequest(
                    TypeMessage.invalidComment
            );
        }
        return this.commentRepo.save(c);
    }

    @Override
    @Cacheable(value = "comment", key = "#id")
    public Comment readById(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequest();
        }
        return this.commentRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "comment", key = "#id")
    })
    public void remove(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequest();
        }
        this.commentRepo.deleteById(id);
    }

}
