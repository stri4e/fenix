package com.github.admins.controllers.impl;

import com.github.admins.dto.CommentDto;
import com.github.admins.payload.Comment;
import com.github.admins.payload.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CommentControllerMocks {

    public static final Long COMMENT_ID = 1L;

    public static final String COMMENT_NAME = "Alex";

    public static final String COMMENT_DESC = "This is supper product";

    public static final Long PRODUCT_ID = 1L;

    public static final String PRODUCT_NAME = "Nokia";

    public static final BigDecimal PRODUCT_PRICE = new BigDecimal("12.2");

    public static final Integer PRODUCT_QUANTITY = 25;

    public static final String PRODUCT_DESCRIPTION = "This is good product.";

    public static final String PRODUCT_DESCRIPTION_FOR_UPDATE = "This is update product.";

    public static final String PRODUCT_PREVIEW_IMAGE = "img";

    public static final List<String> IMAGES = new ArrayList<>() {{
        add("1");
        add("2");
        add("3");
    }};

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

    public static Comment requestPayload() {
        return new Comment(
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

    public static Product product() {
        Product p = new Product();
        p.setId(PRODUCT_ID);
        p.setName(PRODUCT_NAME);
        p.setPrice(PRODUCT_PRICE);
        p.setQuantity(PRODUCT_QUANTITY);
        p.setDescription(PRODUCT_DESCRIPTION_FOR_UPDATE);
        p.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p.setImages(IMAGES);
        return p;
    }

}
