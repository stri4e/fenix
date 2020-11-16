package com.github.products.controllers.impl;

import com.github.products.dto.CommentDto;
import com.github.products.entity.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CommentControllerMocks {

    public static Long SUBCATEGORY_ID = 1L;

    public static String SUBCATEGORY_NAME = "IPhone";

    public static String BRAND_NAME = "OLX";

    public static final Long COMMENT_ID = 1L;

    public static final String COMMENT_NAME = "Alex";

    public static final String COMMENT_DESC = "This is supper product";

    public static String CATEGORY_NAME = "Phone";

    public static final String PRODUCT_NAME = "Nokia";

    public static final BigDecimal PRODUCT_PRICE = new BigDecimal("12.2");

    public static final Integer PRODUCT_QUANTITY = 25;

    public static final String PRODUCT_DESCRIPTION = "This is good product.";

    public static final String PRODUCT_PREVIEW_IMAGE = "img";

    public static final List<String> IMAGES = new ArrayList<>() {{
        add("1");
        add("2");
        add("3");
    }};

    public static CommentDto responsePayload() {
        return new CommentDto(
                COMMENT_ID,
                COMMENT_NAME,
                COMMENT_DESC
        );
    }

    public static CommentDto requestPayload() {
        return new CommentDto(
                COMMENT_NAME,
                COMMENT_DESC
        );
    }

    public static Comment comment() {
        return new Comment(
                COMMENT_ID,
                COMMENT_NAME,
                COMMENT_DESC
        );
    }

    public static Comment commentForSave() {
        return new Comment(
                COMMENT_NAME,
                COMMENT_DESC
        );
    }

    public static Subcategory subcategoryForSave() {
        return new Subcategory(
                SUBCATEGORY_NAME
        );
    }

    public static Subcategory subcategoryForEquals() {
        return new Subcategory(
                SUBCATEGORY_ID,
                SUBCATEGORY_NAME
        );
    }

    public static Category category() {
        return new Category(CATEGORY_NAME);
    }

    public static Brand brand() {
        return new Brand(
                BRAND_NAME
        );
    }

    public static Product product() {
        Product p = new Product();
        p.setName(PRODUCT_NAME);
        p.setPrice(PRODUCT_PRICE);
        p.setQuantity(PRODUCT_QUANTITY);
        p.setDescription(PRODUCT_DESCRIPTION);
        p.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p.setImages(IMAGES);
        p.setStatus(EntityStatus.off);
        return p;
    }

}
