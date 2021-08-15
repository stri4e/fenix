package com.github.products.services.impl;

import com.github.products.entity.Criteria;

import java.util.List;

public class CriteriaServiceMocks {

    public static Criteria entity_toSave() {
        return new Criteria("criteria-test");
    }

    public static Criteria entity_inDatabase() {
        return new Criteria(1L, "criteria-test");
    }

    public static List<Criteria> entities_inDatabase() {
        return List.of(
                new Criteria(1L, "criteria-test-1"),
                new Criteria(2L, "criteria-test-2"),
                new Criteria(3L, "criteria-test-3"),
                new Criteria(4L, "criteria-test-4"),
                new Criteria(5L, "criteria-test-5")
        );
    }

    public static Criteria entity_toUpdate() {
        return new Criteria(1L, "criteria-test-1");
    }

    public static Criteria entity_afterUpdate() {
        return new Criteria(1L, "criteria-test-1");
    }

}
