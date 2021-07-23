package com.github.products.services.impl;

import com.github.products.entity.Comment;

public class CommentServiceMocks {

    public static Comment entity_toSave() {
        return new Comment(
                "comment-name-test",
                "comment text message"
        );
    }

    public static Comment entity_inDatabase() {
        return new Comment(
                1L,
                "comment-name-test",
                "comment text message"
        );
    }

}
