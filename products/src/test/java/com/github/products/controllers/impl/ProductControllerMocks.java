package com.github.products.controllers.impl;

import com.github.products.dto.ProductDto;
import com.github.products.entity.*;
import org.assertj.core.util.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductControllerMocks {

    public static Long SUBCATEGORY_ID = 1L;

    public static String SUBCATEGORY_NAME = "IPhone";

    public static final Long PRODUCT_ID = 1L;

    public static final String PRODUCT_NAME = "Nokia";

    public static final BigDecimal PRODUCT_PRICE = new BigDecimal("12.200");

    public static final Integer PRODUCT_QUANTITY = 25;

    public static final String PRODUCT_DESCRIPTION = "This is good product.";

    public static final String PRODUCT_DESCRIPTION_FOR_UPDATE = "This is good product for your life.";

    public static final String PRODUCT_PREVIEW_IMAGE = "img";

    public static String BRAND_NAME = "OLX";

    public static String CATEGORY_NAME = "Phone";

    public static final List<String> IMAGES = Lists.newArrayList("1", "2", "3");

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
        return p;
    }

    public static ProductDto requestProduct1() {
        ProductDto p = new ProductDto();
        p.setName(PRODUCT_NAME);
        p.setBrandName(BRAND_NAME);
        p.setPrice(PRODUCT_PRICE);
        p.setQuantity(PRODUCT_QUANTITY);
        p.setDescription(PRODUCT_DESCRIPTION);
        p.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p.setImages(IMAGES);
        p.setSubcategoryName(SUBCATEGORY_NAME);
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
        p.setSubcategory(subcategoryForEquals());
        return p;
    }

    public static ProductDto responseProduct1() {
        ProductDto p = new ProductDto();
        p.setId(PRODUCT_ID);
        p.setName(PRODUCT_NAME);
        p.setBrandName(BRAND_NAME);
        p.setPrice(PRODUCT_PRICE);
        p.setQuantity(PRODUCT_QUANTITY);
        p.setDescription(PRODUCT_DESCRIPTION);
        p.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p.setImages(IMAGES);
        p.setSubcategoryName(SUBCATEGORY_NAME);
        p.setSpecifications(new ArrayList<>());
        p.setComments(new ArrayList<>());
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
        p.setSubcategory(subcategoryForEquals());
        return p;
    }

    public static ProductDto responseProductForUpdate1() {
        ProductDto p = new ProductDto();
        p.setId(PRODUCT_ID);
        p.setBrandName(BRAND_NAME);
        p.setSubcategoryName(SUBCATEGORY_NAME);
        p.setName(PRODUCT_NAME);
        p.setPrice(PRODUCT_PRICE);
        p.setQuantity(PRODUCT_QUANTITY);
        p.setDescription(PRODUCT_DESCRIPTION_FOR_UPDATE);
        p.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p.setImages(IMAGES);
        return p;
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
