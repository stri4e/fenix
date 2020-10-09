package com.github.products.controllers.impl;

import com.github.products.entity.Category;
import com.github.products.entity.Product;
import com.github.products.entity.EntityStatus;
import org.assertj.core.util.Lists;

import java.math.BigDecimal;
import java.util.List;

public class ProductControllerMocks {

    public static final Long CATEGORY_ID = 1L;

    public static final String CATEGORY = "Phone";

    public static final Long PRODUCT_ID = 1L;

    public static final String PRODUCT_NAME = "Nokia";

    public static final BigDecimal PRODUCT_PRICE = new BigDecimal("12.2");

    public static final Integer PRODUCT_QUANTITY = 25;

    public static final String PRODUCT_DESCRIPTION = "This is good product.";

    public static final String PRODUCT_DESCRIPTION_FOR_UPDATE = "This is good product for your life.";

    public static final String PRODUCT_PREVIEW_IMAGE = "img";


    public static final List<String> IMAGES = Lists.newArrayList("1", "2", "3");

    public static Category categoryForSave() {
        return new Category(CATEGORY);
    }

    public static Category category() {
        return new Category(CATEGORY_ID, CATEGORY);
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

    public static Product requestProduct() {
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

    public static Product responseProduct() {
        Product p = new Product();
        p.setId(PRODUCT_ID);
        p.setName(PRODUCT_NAME);
        p.setPrice(PRODUCT_PRICE);
        p.setQuantity(PRODUCT_QUANTITY);
        p.setDescription(PRODUCT_DESCRIPTION);
        p.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p.setImages(IMAGES);
        p.setStatus(EntityStatus.off);
        p.setCategory(category());
        return p;
    }

    public static Product responseProductForUpdate() {
        Product p = new Product();
        p.setId(PRODUCT_ID);
        p.setName(PRODUCT_NAME);
        p.setPrice(PRODUCT_PRICE);
        p.setQuantity(PRODUCT_QUANTITY);
        p.setDescription(PRODUCT_DESCRIPTION_FOR_UPDATE);
        p.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p.setImages(IMAGES);
        p.setStatus(EntityStatus.off);
        p.setCategory(category());
        return p;
    }

}
