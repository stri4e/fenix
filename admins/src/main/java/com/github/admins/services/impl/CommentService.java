package com.github.admins.services.impl;

import com.github.admins.payload.Comment;
import com.github.admins.services.ICommentService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService implements ICommentService {

    private static final Comment EMPTY = new Comment();

    @Override
    public Optional<Comment> create(Comment c) {
        return Optional.empty();
    }

    @Override
    public Optional<Comment> readById(Long id) {
        return Optional.empty();
    }

    @Override
    public void remove(Long id) {

    }
}
