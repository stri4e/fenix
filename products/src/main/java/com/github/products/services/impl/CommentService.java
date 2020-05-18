package com.github.products.services.impl;

import com.github.products.entity.Comment;
import com.github.products.exceptions.BadRequest;
import com.github.products.exceptions.NotFound;
import com.github.products.exceptions.TypeMessage;
import com.github.products.repository.CommentRepo;
import com.github.products.services.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {

    private final CommentRepo commentRepo;

    @Override
    public Comment create(Comment c) {
        if (Objects.isNull(c)) {
            throw new BadRequest(
                    TypeMessage.invalidComment
            );
        }
        return this.commentRepo.save(c);
    }

    @Override
    public Comment readById(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequest();
        }
        return this.commentRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void remove(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequest();
        }
        this.commentRepo.deleteById(id);
    }

}
