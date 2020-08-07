package com.github.admins.controllers.impl;

import com.github.admins.dto.ProductDto;
import com.github.admins.payload.Category;
import com.github.admins.payload.Product;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomProductControllerMocks {

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

    public static final List<ProductDto> PRODUCTS = Lists.newArrayList(
        new ProductDto(
                1L,
                "Nokia",
                new BigDecimal("12.2"),
                25,
                "This is good product.",
                "img",
                IMAGES
        ),
        new ProductDto(
                2L,
                "IPhone",
                new BigDecimal("100.2"),
                100,
                "This is good product.",
                "img",
                IMAGES
        ),
        new ProductDto(
                3L,
                "Sumsung",
                new BigDecimal("50.2"),
                500,
                "This is good product.",
                "img",
                IMAGES
        ),
        new ProductDto(
                4L,
                "Xiaomi",
                new BigDecimal("14.2"),
                100,
                "This is good product.",
                "img",
                IMAGES
        ),
        new ProductDto(
                5L,
                "Huawei",
                new BigDecimal("24.2"),
                560,
                "This is good product.",
                "img",
                IMAGES
        )
    );

    public static String CATEGORY_NAME = "Phone";

    public static ProductDto productDtoWithoutId() {
        ProductDto p = new ProductDto();
        p.setName(PRODUCT_NAME);
        p.setPrice(PRODUCT_PRICE);
        p.setQuantity(PRODUCT_QUANTITY);
        p.setDescription(PRODUCT_DESCRIPTION);
        p.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p.setImages(IMAGES);
        return p;
    }

    public static ProductDto productDto() {
        ProductDto p = new ProductDto();
        p.setId(PRODUCT_ID);
        p.setName(PRODUCT_NAME);
        p.setPrice(PRODUCT_PRICE);
        p.setQuantity(PRODUCT_QUANTITY);
        p.setDescription(PRODUCT_DESCRIPTION);
        p.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p.setImages(IMAGES);
        return p;
    }

    public static ProductDto getProductDtoForUpdate() {
        ProductDto p = new ProductDto();
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
