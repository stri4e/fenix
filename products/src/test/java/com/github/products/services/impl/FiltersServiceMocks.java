package com.github.products.services.impl;

import com.github.products.entity.Filter;

public class FiltersServiceMocks {

    public static Filter beforeCreate() {
        return new Filter("filter-test");
    }

    public static Filter afterCreate() {
        return new Filter(1L, "filter-test");
    }

    public static Filter toReadById() {
        return new Filter(1L, "filter-test");
    }

    public static Filter toUpdate() {
        return new Filter(1L, "filter-test");
    }

}
