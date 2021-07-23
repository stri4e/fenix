package com.github.products.services.impl;

import com.github.products.entity.Comment;
import com.github.products.entity.EntityStatus;
import com.github.products.exceptions.EntityBadRequest;
import com.github.products.exceptions.EntityNotFound;
import com.github.products.exceptions.ParametersBadRequest;
import com.github.products.repository.CommentRepo;
import com.github.products.services.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.github.products.utils.NullSafety.requiredNotNull;
import static com.github.products.utils.NullSafety.throwIfNull;

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
        throwIfNull(c, EntityBadRequest::new);
        return this.commentRepo.save(c);
    }

    @Override
    @Cacheable(value = "comment", key = "#id")
    public Comment readById(Long id) {
        return this.commentRepo.findById(
                requiredNotNull(id, ParametersBadRequest::new)
        ).orElseThrow(EntityNotFound::new);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "comment", key = "#id")
    })
    public void remove(Long id) {
        this.commentRepo.updateStatus(
                requiredNotNull(id, ParametersBadRequest::new),
                EntityStatus.off
        );
    }

}
