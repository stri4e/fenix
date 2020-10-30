package com.github.products.controllers.impl;

import com.github.products.entity.Criteria;
import com.github.products.entity.Filter;

import java.util.List;

public class FiltersControllerMocks {

    //==================================================
    //================== FILTER ========================
    //==================================================

    public static Long FILTER_ID = 1L;

    public static String FILTER_TITLE = "Phone";

    public static Long FILTER_ID_ONE = 1L;

    public static String FILTER_TITLE_ONE = "Phone";

    public static Long FILTER_ID_TWO = 2L;

    public static String FILTER_TITLE_TWO = "Car";

    public static Long FILTER_ID_THREE = 3L;

    public static String FILTER_TITLE_THREE = "Leptop";

    public static Long FILTER_ID_FOUR = 4L;

    public static String FILTER_TITLE_FOUR = "Headphone";

    public static Long FILTER_ID_FIVE = 5L;

    public static String FILTER_TITLE_FIVE = "Bicikle";

    //==================================================
    //================== CRITERIA ======================
    //==================================================

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

    //==================================================
    //============= FILTER FOR SAVE ====================
    //==================================================

    public static Filter filterForSave() {
        return new Filter(
                FILTER_ID,
                FILTER_TITLE,
                criteriaForSave
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

    //==================================================
    //============= MANY FILTERS FOR SAVE ==============
    //==================================================

    public static Filter filterOneForSave() {
        return new Filter(
                FILTER_TITLE_ONE,
                criteriaForSaveOne
        );
    }

    public static List<Criteria> criteriaForSaveOne = List.of(
            criteriaOneForSave(),
            criteriaTwoForSave()
    );

    public static Filter filterTwoForSave() {
        return new Filter(
                FILTER_TITLE_TWO,
                criteriaForSaveTwo
        );
    }

    public static List<Criteria> criteriaForSaveTwo = List.of(
            criteriaOneForSave(),
            criteriaFourForSave(),
            criteriaFiveForSave()
    );

    public static Filter filterThreeForSave() {
        return new Filter(
                FILTER_TITLE_THREE,
                criteriaForSaveThree
        );
    }

    public static List<Criteria> criteriaForSaveThree = List.of(
            criteriaThreeForSave(),
            criteriaFourForSave(),
            criteriaFiveForSave()
    );

    public static Filter filterFourForSave() {
        return new Filter(
                FILTER_TITLE_FOUR,
                criteriaForSaveFour
        );
    }

    public static List<Criteria> criteriaForSaveFour = List.of(
            criteriaOneForSave(),
            criteriaFiveForSave()
    );

    public static Filter filterFiveForSave() {
        return new Filter(
                FILTER_TITLE_FIVE,
                criteriaForSaveFive
        );
    }

    public static List<Criteria> criteriaForSaveFive = List.of(
            criteriaFourForSave(),
            criteriaFiveForSave()
    );

    public static List<Filter> filtersForSave = List.of(
            filterOneForSave(),
            filterTwoForSave(),
            filterThreeForSave(),
            filterFourForSave(),
            filterFiveForSave()
    );

    //==================================================
    //============= FILTERS FOR EQUALS =================
    //==================================================

    public static Filter filterForEquals() {
        return new Filter(
                FILTER_ID_ONE,
                FILTER_TITLE_ONE,
                criteriaForEquals
        );
    }

    //==================================================
    //============= MANY FILTERS FOR EQUALS ============
    //==================================================

    public static Filter filterOneForEquals() {
        return new Filter(
                FILTER_ID_ONE,
                FILTER_TITLE_ONE,
                criteriaForEqualsOne
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

    public static List<Criteria> criteriaForEqualsOne = List.of(
            criteriaOneForSave(),
            criteriaTwoForSave()
    );

    public static Filter filterTwoForEquals() {
        return new Filter(
                FILTER_ID_TWO,
                FILTER_TITLE_TWO,
                criteriaForEqualsTwo
        );
    }

    public static List<Criteria> criteriaForEqualsTwo = List.of(
            criteriaOneForEquals(),
            criteriaFourForEquals(),
            criteriaFiveForEquals()
    );

    public static Filter filterThreeForEquals() {
        return new Filter(
                FILTER_ID_THREE,
                FILTER_TITLE_THREE,
                criteriaForEqualsThree
        );
    }

    public static List<Criteria> criteriaForEqualsThree = List.of(
            criteriaThreeForEquals(),
            criteriaFourForEquals(),
            criteriaFiveForEquals()
    );

    public static Filter filterFourForEquals() {
        return new Filter(
                FILTER_ID_FOUR,
                FILTER_TITLE_FOUR,
                criteriaForEqualsFour
        );
    }

    public static List<Criteria> criteriaForEqualsFour = List.of(
            criteriaOneForEquals(),
            criteriaFiveForEquals()
    );

    public static Filter filterFiveForEquals() {
        return new Filter(
                FILTER_ID_FIVE,
                FILTER_TITLE_FIVE,
                criteriaForEqualsFive
        );
    }

    public static List<Criteria> criteriaForEqualsFive = List.of(
            criteriaFourForEquals(),
            criteriaFiveForEquals()
    );

    public static List<Filter> filtersForEquals = List.of(
            filterOneForSave(),
            filterTwoForSave(),
            filterThreeForSave(),
            filterFourForSave(),
            filterFiveForSave()
    );

}
