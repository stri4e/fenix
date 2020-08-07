package com.github.admins.controllers.impl;

import com.github.admins.dto.CommentDto;

public class CommentControllerMocks {

    public static final Long COMMENT_ID = 1L;

    public static final String COMMENT_NAME = "Alex";

    public static final String COMMENT_DESC = "This is supper product";

    public static CommentDto expCommentDto() {
        return new CommentDto(
                COMMENT_ID,
                COMMENT_NAME,
                COMMENT_DESC
        );
    }

    public static CommentDto commentDto() {
        return new CommentDto(
                COMMENT_NAME,
                COMMENT_DESC
        );
    }

}
