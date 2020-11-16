package com.github.admins.controllers.impl;

import com.github.admins.dto.CriteriaDto;

import java.util.List;

public class CriteriaControllerMocks {

    public static List<Long> CRITERIA_IDS = List.of(1L, 2L, 3L, 4L, 5L);

    public static CriteriaDto request() {
        return new CriteriaDto(
            null, "50 zolota"
        );
    }

    public static CriteriaDto response() {
        return new CriteriaDto(
                1L, "50 zolota"
        );
    }

}
