package com.github.products.controllers.impl;

import com.github.products.dto.ProductDto;
import com.github.products.entity.*;
import com.google.common.collect.Sets;
import org.assertj.core.util.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public static final Long BRAND_ID = 1L;

    public static final String BRAND_NAME = "OLX";

    public static final List<String> IMAGES = Lists.newArrayList("1", "2", "3");

    public static Long CRITERIA_ID_ONE = 1L;

    public static String CRITERIA_VALUE_ONE = "50";

    public static Long CRITERIA_ID_TWO = 2L;

    public static String CRITERIA_VALUE_TWO = "150";

    public static Long CRITERIA_ID_THREE = 3L;

    public static String CRITERIA_VALUE_THREE = "350";

    public static Long CRITERIA_ID_FOUR = 4L;

    public static String CRITERIA_VALUE_FOUR = "150";

    public static Long CRITERIA_ID_FIVE = 5L;

    public static String CRITERIA_VALUE_FIVE = "1150";

    public static Subcategory subcategoryForSave() {
        return new Subcategory(
                SUBCATEGORY_NAME
        );
    }

    public static Subcategory subcategory() {
        return new Subcategory(
                SUBCATEGORY_ID,
                SUBCATEGORY_NAME
        );
    }

    public static Product productForCreate() {
        Product p = new Product();
        p.setName(PRODUCT_NAME);
        p.setPrice(PRODUCT_PRICE);
//        p.setQuantity(PRODUCT_QUANTITY);
        p.setDescription(PRODUCT_DESCRIPTION);
        p.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p.setImages(IMAGES);
        return p;
    }

    public static ProductDto requestProduct() {
        ProductDto p = new ProductDto();
        p.setName(PRODUCT_NAME);
        p.setBrandName(BRAND_NAME);
        p.setPrice(PRODUCT_PRICE);
//        p.setQuantity(PRODUCT_QUANTITY);
        p.setDescription(PRODUCT_DESCRIPTION);
        p.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p.setImages(IMAGES);
//        p.setSubcategoryName(SUBCATEGORY_NAME);
        return p;
    }

    public static ProductDto responseProduct() {
        ProductDto p = new ProductDto();
        p.setId(PRODUCT_ID);
        p.setName(PRODUCT_NAME);
        p.setBrandName(BRAND_NAME);
        p.setPrice(PRODUCT_PRICE);
//        p.setQuantity(PRODUCT_QUANTITY);
        p.setDescription(PRODUCT_DESCRIPTION);
        p.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p.setImages(IMAGES);
//        p.setSubcategoryName(SUBCATEGORY_NAME);
        p.setSpecifications(new ArrayList<>());
        p.setComments(new ArrayList<>());
        return p;
    }

    public static ProductDto responseProductForUpdate() {
        ProductDto p = new ProductDto();
        p.setId(PRODUCT_ID);
        p.setBrandName(BRAND_NAME);
//        p.setSubcategoryName(SUBCATEGORY_NAME);
        p.setName(PRODUCT_NAME);
        p.setPrice(PRODUCT_PRICE);
//        p.setQuantity(PRODUCT_QUANTITY);
        p.setDescription(PRODUCT_DESCRIPTION_FOR_UPDATE);
        p.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p.setImages(IMAGES);
        return p;
    }

    public static Brand brandForSave() {
        return new Brand(
                BRAND_NAME
        );
    }

    public static Brand brand() {
        return new Brand(
                BRAND_ID,
                BRAND_NAME
        );
    }

    public static List<ProductDto> products() {
        ProductDto p1 = new ProductDto();
        p1.setId(1L);
        p1.setName("Nokia");
        p1.setPrice(new BigDecimal("12.200"));
//        p1.setQuantity(25);
        p1.setDescription("This is phone for coll.");
        p1.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p1.setImages(IMAGES);
        p1.setBrandName(BRAND_NAME);
//        p1.setSubcategoryName(SUBCATEGORY_NAME);
        p1.setComments(new ArrayList<>());
        p1.setSpecifications(new ArrayList<>());

        ProductDto p2 = new ProductDto();
        p2.setId(2L);
        p2.setName("Sumsung");
        p2.setPrice(new BigDecimal("5.200"));
//        p2.setQuantity(190);
        p2.setDescription("This is phone for coll.");
        p2.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p2.setImages(IMAGES);
        p2.setBrandName(BRAND_NAME);
//        p2.setSubcategoryName(SUBCATEGORY_NAME);
        p2.setComments(new ArrayList<>());
        p2.setSpecifications(new ArrayList<>());

        ProductDto p3 = new ProductDto();
        p3.setId(3L);
        p3.setName("Xiaomi");
        p3.setPrice(new BigDecimal("50.200"));
//        p3.setQuantity(1220);
        p3.setDescription("This is phone for coll.");
        p3.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p3.setImages(IMAGES);
        p3.setBrandName(BRAND_NAME);
//        p3.setSubcategoryName(SUBCATEGORY_NAME);
        p3.setComments(new ArrayList<>());
        p3.setSpecifications(new ArrayList<>());

        ProductDto p4 = new ProductDto();
        p4.setId(4L);
        p4.setName("IPhone");
        p4.setPrice(new BigDecimal("120.200"));
//        p4.setQuantity(520);
        p4.setDescription("This is phone for coll.");
        p4.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p4.setImages(IMAGES);
        p4.setBrandName(BRAND_NAME);
//        p4.setSubcategoryName(SUBCATEGORY_NAME);
        p4.setComments(new ArrayList<>());
        p4.setSpecifications(new ArrayList<>());

        return Lists.newArrayList(p1, p2, p3, p4);
    }

    public static List<Product> productsForSave() {
        Product p1 = new Product();
        p1.setName("Nokia");
        p1.setPrice(new BigDecimal("12.200"));
//        p1.setQuantity(25);
        p1.setDescription("This is phone for coll.");
        p1.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p1.setImages(IMAGES);
        p1.setBrand(brand());
        p1.setSubcategory(subcategory());

        Product p2 = new Product();
        p2.setName("Sumsung");
        p2.setPrice(new BigDecimal("5.200"));
//        p2.setQuantity(190);
        p2.setDescription("This is phone for coll.");
        p2.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p2.setImages(IMAGES);
        p2.setBrand(brand());
        p2.setSubcategory(subcategory());

        Product p3 = new Product();
        p3.setName("Xiaomi");
        p3.setPrice(new BigDecimal("50.200"));
//        p3.setQuantity(1220);
        p3.setDescription("This is phone for coll.");
        p3.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p3.setImages(IMAGES);
        p3.setBrand(brand());
        p3.setSubcategory(subcategory());

        Product p4 = new Product();
        p4.setName("IPhone");
        p4.setPrice(new BigDecimal("120.200"));
//        p4.setQuantity(520);
        p4.setDescription("This is phone for coll.");
        p4.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p4.setImages(IMAGES);
        p4.setBrand(brand());
        p4.setSubcategory(subcategory());

        return Lists.newArrayList(p1, p2, p3, p4);
    }

    public static List<Product> productsForSaveWithStatusOn() {
        Product p1 = new Product();
        p1.setName("Nokia");
        p1.setPrice(new BigDecimal("12.200"));
//        p1.setQuantity(25);
        p1.setDescription("This is phone for coll.");
        p1.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p1.setImages(IMAGES);
        p1.setStatus(EntityStatus.on);
        p1.setBrand(brand());
        p1.setSubcategory(subcategory());

        Product p2 = new Product();
        p2.setName("Sumsung");
        p2.setPrice(new BigDecimal("5.200"));
//        p2.setQuantity(190);
        p2.setDescription("This is phone for coll.");
        p2.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p2.setImages(IMAGES);
        p2.setStatus(EntityStatus.on);
        p2.setBrand(brand());
        p2.setSubcategory(subcategory());

        Product p3 = new Product();
        p3.setName("Xiaomi");
        p3.setPrice(new BigDecimal("50.200"));
//        p3.setQuantity(1220);
        p3.setDescription("This is phone for coll.");
        p3.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p3.setImages(IMAGES);
        p3.setStatus(EntityStatus.on);
        p3.setBrand(brand());
        p3.setSubcategory(subcategory());

        Product p4 = new Product();
        p4.setName("IPhone");
        p4.setPrice(new BigDecimal("120.200"));
//        p4.setQuantity(520);
        p4.setDescription("This is phone for coll.");
        p4.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p4.setImages(IMAGES);
        p4.setStatus(EntityStatus.on);
        p4.setBrand(brand());
        p4.setSubcategory(subcategory());

        return Lists.newArrayList(p1, p2, p3, p4);
    }

    public static List<Product> productsForSaveWithCriteria() {
        Product p1 = new Product();
        p1.setName("Nokia");
        p1.setPrice(new BigDecimal("12.200"));
//        p1.setQuantity(25);
        p1.setDescription("This is phone for coll.");
        p1.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p1.setImages(IMAGES);
        p1.setBrand(brand());
        p1.setSubcategory(subcategory());

        Product p2 = new Product();
        p2.setName("Sumsung");
        p2.setPrice(new BigDecimal("5.200"));
        p2.setDescription("This is phone for coll.");
        p2.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p2.setImages(IMAGES);
        p2.setBrand(brand());
        p2.setSubcategory(subcategory());

        Product p3 = new Product();
        p3.setName("Xiaomi");
        p3.setPrice(new BigDecimal("50.200"));
        p3.setDescription("This is phone for coll.");
        p3.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p3.setImages(IMAGES);
        p3.setBrand(brand());
        p3.setSubcategory(subcategory());

        Product p4 = new Product();
        p4.setName("IPhone");
        p4.setPrice(new BigDecimal("120.200"));
        p4.setDescription("This is phone for coll.");
        p4.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p4.setImages(IMAGES);
        p4.setBrand(brand());
        p4.setSubcategory(subcategory());

        return Lists.newArrayList(p1, p2, p3, p4);
    }

    //===========================================
    //============ Criteria for Equals ==========
    //===========================================

    public static Criteria criteriaOneForEquals() {
        return new Criteria(
                CRITERIA_ID_ONE,
                CRITERIA_VALUE_ONE
        );
    }

    public static Criteria criteriaTwoForEquals() {
        return new Criteria(
                CRITERIA_ID_TWO,
                CRITERIA_VALUE_TWO
        );
    }

    public static Criteria criteriaThreeForEquals() {
        return new Criteria(
                CRITERIA_ID_THREE,
                CRITERIA_VALUE_THREE
        );
    }

    public static Criteria criteriaFourForEquals() {
        return new Criteria(
                CRITERIA_ID_FOUR,
                CRITERIA_VALUE_FOUR
        );
    }

    public static Criteria criteriaFiveForEquals() {
        return new Criteria(
                CRITERIA_ID_FIVE,
                CRITERIA_VALUE_FIVE
        );
    }

    public static Set<Criteria> criteriaForEquals = Sets.newHashSet(
            criteriaOneForEquals(),
            criteriaTwoForEquals(),
            criteriaThreeForEquals(),
            criteriaFourForEquals(),
            criteriaFiveForEquals()
    );

    //===========================================
    //============ Criteria for Save ============
    //===========================================

    public static Criteria criteriaOneForSave() {
        return new Criteria(
                CRITERIA_VALUE_ONE
        );
    }

    public static Criteria criteriaTwoForSave() {
        return new Criteria(
                CRITERIA_VALUE_TWO
        );
    }

    public static Criteria criteriaThreeForSave() {
        return new Criteria(
                CRITERIA_VALUE_THREE
        );
    }

    public static Criteria criteriaFourForSave() {
        return new Criteria(
                CRITERIA_VALUE_FOUR
        );
    }

    public static Criteria criteriaFiveForSave() {
        return new Criteria(
                CRITERIA_VALUE_FIVE
        );
    }

    public static List<Criteria> criteriaForSave = List.of(
            criteriaOneForSave(),
            criteriaTwoForSave(),
            criteriaThreeForSave(),
            criteriaFourForSave(),
            criteriaFiveForSave()
    );

}
