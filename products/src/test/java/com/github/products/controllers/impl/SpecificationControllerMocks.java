package com.github.products.controllers.impl;

import com.github.products.dto.SpecificationDto;
import com.github.products.entity.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SpecificationControllerMocks {

    public static Long SUBCATEGORY_ID = 1L;

    public static String SUBCATEGORY_NAME = "IPhone";

    public static String BRAND_NAME = "OLX";

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

    public static Specification specificationForSave() {
        return new Specification(
                SPECIFICATION_NAME,
                SPECIFICATION_DESCRIPTION
        );
    }

    public static SpecificationDto requestPayload() {
        return new SpecificationDto(
                null,
                SPECIFICATION_NAME,
                SPECIFICATION_DESCRIPTION
        );
    }

    public static Specification specificationForEquals() {
        return new Specification(
                ID,
                SPECIFICATION_NAME,
                SPECIFICATION_DESCRIPTION
        );
    }

    public static SpecificationDto responsePayload() {
        return new SpecificationDto(
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
        p.setDescription(PRODUCT_DESCRIPTION);
        p.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p.setImages(IMAGES);
        p.setStatus(EntityStatus.on);
        return p;
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

}
