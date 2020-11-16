package com.github.admins.controllers.impl;

import com.github.admins.dto.CriteriaDto;
import com.github.admins.dto.FilterDto;
import com.github.admins.dto.SubcategoryDto;

import java.util.List;

public class SubcategoryControllerMocks {

    public static SubcategoryDto request() {
        return new SubcategoryDto(
                1L, "subcategory", FILTERS
        );
    }

    public static SubcategoryDto response() {
        return new SubcategoryDto(
                1L, "subcategory", FILTERS
        );
    }

    public static List<FilterDto> FILTERS = List.of(
            one(), two(), three()
    );

    public static FilterDto one() {
        return new FilterDto(
                1L, "title1", CRITERIA
        );
    }

    public static FilterDto two() {
        return new FilterDto(
                1L, "title1", CRITERIA
        );
    }

    public static FilterDto three() {
        return new FilterDto(
                1L, "title1", CRITERIA
        );
    }

    public static List<CriteriaDto> CRITERIA = List.of(
            criteriaOne(), criteriaTwo(), criteriaThree()
    );

    public static CriteriaDto criteriaOne() {
        return new CriteriaDto(
                1L, "50 zolota"
        );
    }

    public static CriteriaDto criteriaTwo() {
        return new CriteriaDto(
                1L, "50 zolota"
        );
    }

    public static CriteriaDto criteriaThree() {
        return new CriteriaDto(
                1L, "50 zolota"
        );
    }

}
