package com.github.admins.services.impl;

import com.github.admins.payload.Comment;
import com.github.admins.services.ICommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService {

    private static final Comment EMPTY = new Comment();

    @Override
    public Comment create(Comment c) {
        return EMPTY;
    }

    @Override
    public Comment readById(Long id) {
        return EMPTY;
    }

    @Override
    public void remove(Long id) {

    }
}
