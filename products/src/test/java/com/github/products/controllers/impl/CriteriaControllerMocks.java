package com.github.products.controllers.impl;

import com.github.products.entity.Criteria;

import java.util.List;

public class CriteriaControllerMocks {

    public static Long CRITERIA_ID = 1L;

    public static String CRITERIA_VALUE = "50";

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

    public static Criteria criteriaForSave() {
        return new Criteria(
                CRITERIA_VALUE
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
