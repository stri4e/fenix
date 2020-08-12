package com.github.admins.controllers.impl;

import com.github.admins.dto.CommentDto;
import com.github.admins.payload.Comment;

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

    public static Comment responsePayload() {
        return new Comment(
                COMMENT_ID,
                COMMENT_NAME,
                COMMENT_DESC
        );
    }

}
