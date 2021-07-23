package com.github.products.services.impl;

import com.github.products.entity.Comment;
import com.github.products.entity.EntityStatus;
import com.github.products.exceptions.EntityNotFound;
import com.github.products.repository.CommentRepo;
import com.github.products.services.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@CacheConfig(cacheNames = "comment")
public class CommentService implements ICommentService {

    private final CommentRepo commentRepo;

    @Override
    @Caching(
            put = @CachePut(value = "comment", key = "#c.id")
    )
    public Comment create(Comment c) {
        return this.commentRepo.save(c);
    }

    @Override
    @Cacheable(value = "comment", key = "#id")
    public Comment readById(Long id) {
        return this.commentRepo.findById(id)
                .orElseThrow(EntityNotFound::new);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "comment", key = "#id")
    })
    public void remove(Long id) {
        this.commentRepo.updateStatus(id, EntityStatus.off);
    }

}
