package com.github.products.controllers.impl;

import com.github.products.dto.CriteriaDto;
import com.github.products.dto.FilterDto;
import com.github.products.entity.*;
import org.assertj.core.util.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CriteriaControllerMocks {

    public static Long CRITERIA_ID = 1L;

    public static String CRITERIA_VALUE = "50";

    public static Long CRITERIA_ID_ONE = 1L;

    public static String CRITERIA_VALUE_ONE = "50";

    public static String CRITERIA_VALUE_FOR_UPDATE = "323150";

    public static Long CRITERIA_ID_TWO = 2L;

    public static String CRITERIA_VALUE_TWO = "150";

    public static Long CRITERIA_ID_THREE = 3L;

    public static String CRITERIA_VALUE_THREE = "350";

    public static Long CRITERIA_ID_FOUR = 4L;

    public static String CRITERIA_VALUE_FOUR = "150";

    public static Long CRITERIA_ID_FIVE = 5L;

    public static String CRITERIA_VALUE_FIVE = "1150";

    public static String FILTER_TITLE = "Phone";

    public static final String PRODUCT_NAME = "Nokia";

    public static final BigDecimal PRODUCT_PRICE = new BigDecimal("12.200");

    public static final Integer PRODUCT_QUANTITY = 25;

    public static final String PRODUCT_DESCRIPTION = "This is good product.";

    public static final String PRODUCT_PREVIEW_IMAGE = "img";

    public static final List<String> IMAGES = Lists.newArrayList("1", "2", "3");

    public static final String BRAND_NAME = "OLX";

    public static String SUBCATEGORY_NAME = "IPhone";

    public static List<Long> IDS = List.of(1L, 2L, 3L, 4L, 5L);

    public static Product productForCreate() {
        Product p = new Product();
        p.setName(PRODUCT_NAME);
        p.setPrice(PRODUCT_PRICE);
        p.setQuantity(PRODUCT_QUANTITY);
        p.setDescription(PRODUCT_DESCRIPTION);
        p.setPreviewImage(PRODUCT_PREVIEW_IMAGE);
        p.setImages(IMAGES);
        return p;
    }

    public static Brand brandForSave() {
        return new Brand(
                BRAND_NAME
        );
    }

    public static Subcategory subcategoryForSave() {
        return new Subcategory(
                SUBCATEGORY_NAME
        );
    }

    public static Criteria criteriaForSave() {
        return new Criteria(
                CRITERIA_VALUE
        );
    }

    public static CriteriaDto requestCriteria() {
        return new CriteriaDto(
                CRITERIA_VALUE_ONE
        );
    }

    public static CriteriaDto criteriaForUpdate() {
        return new CriteriaDto(
                CRITERIA_ID, CRITERIA_VALUE_FOR_UPDATE
        );
    }

    public static CriteriaDto responseForEquals() {
        return new CriteriaDto(
               CRITERIA_ID, CRITERIA_VALUE_ONE
        );
    }

    public static Filter filterForSave() {
        return new Filter(
                FILTER_TITLE,
                new ArrayList<>()
        );
    }

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


    public static Criteria criteriaForEquals() {
        return new Criteria(
                CRITERIA_ID,
                CRITERIA_VALUE
        );
    }

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

    public static List<Criteria> criteriaForEquals = List.of(
            criteriaOneForEquals(),
            criteriaTwoForEquals(),
            criteriaThreeForEquals(),
            criteriaFourForEquals(),
            criteriaFiveForEquals()
    );

}
