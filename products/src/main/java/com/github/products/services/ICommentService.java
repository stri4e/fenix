package com.github.products.services;

import com.github.products.entity.Comment;

public interface ICommentService {

    Comment create(Comment c);

    Comment readById(Long id);

    void remove(Long id);

}
