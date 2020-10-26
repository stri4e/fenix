package com.github.admins.controllers.impl;

import com.github.admins.dto.SpecificationDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SpecificationControllerMocks {

    public static final Long ID = 1L;

    public static final String SPECIFICATION_NAME = "Phone";

    public static final String SPECIFICATION_DESCRIPTION = "This is phone spec.";

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

    public static SpecificationDto expSpec() {
        return new SpecificationDto(
                ID,
                SPECIFICATION_NAME,
                SPECIFICATION_DESCRIPTION
        );
    }

    public static SpecificationDto payload() {
        return new SpecificationDto(
                SPECIFICATION_NAME,
                SPECIFICATION_DESCRIPTION
        );
    }

    public static SpecificationDto payloadWithId() {
        return new SpecificationDto(
                ID,
                SPECIFICATION_NAME,
                SPECIFICATION_DESCRIPTION
        );
    }

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
