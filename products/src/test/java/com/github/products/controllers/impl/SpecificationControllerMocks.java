package com.github.products.controllers.impl;

import com.github.products.entity.Category;
import com.github.products.entity.Product;
import com.github.products.entity.ProductStatus;
import com.github.products.entity.Specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpecificationControllerMocks {

    public static final Long ID = 1L;

    public static final String SPECIFICATION_NAME = "Phone";

    public static final String SPECIFICATION_DESCRIPTION = "This is phone spec.";

    public static final String SPECIFICATION_DESCRIPTION_FOR_UPDATE = "This is phone specification.";

    public static final Long PRODUCT_ID = 1L;

    public static final String PRODUCT_NAME = "Nokia";

    public static final BigDecimal PRODUCT_PRICE = new BigDecimal("12.2");

    public static final Integer PRODUCT_QUANTITY = 25;

    public static final String PRODUCT_DESCRIPTION = "This is good product.";

    public static final String PRODUCT_PREVIEW_IMAGE = "img";

    public static String CATEGORY_NAME = "Phone";

    public static final List<String> IMAGES = new ArrayList<>() {{
        add("1");
        add("2");
        add("3");
    }};

    public static Specification requestPayload() {
        return new Specification(
                SPECIFICATION_NAME,
                SPECIFICATION_DESCRIPTION
        );
    }

    public static Specification responsePayload() {
        return new Specification(
                ID,
                SPECIFICATION_NAME,
                SPECIFICATION_DESCRIPTION
        );
    }

    public static Specification specification() {
        return new Specification(
                ID,
                SPECIFICATION_NAME,
                SPECIFICATION_DESCRIPTION_FOR_UPDATE
        );
    }

    public static Product product() {
        Product p = new Product();
        p.setId(PRODUCT_ID);
        p.setName(PRODUCT_NAME);
        p.setPrice(PRODUCT_PRICE);
        p.setQuantity(PRODUCT_QUANTITY);
        p.setDescription(PRODUCT_DESCRIPTION);
        p.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p.setImages(IMAGES);
        p.setStatus(ProductStatus.used);
        p.setCreateDate(new Date());
        return p;
    }

    public static Category category() {
        return new Category(CATEGORY_NAME);
    }

}
