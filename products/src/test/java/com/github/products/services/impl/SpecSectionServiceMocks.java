package com.github.products.services.impl;

import com.github.products.entity.SpecificationSection;

public class SpecSectionServiceMocks {

    public static SpecificationSection entityBeforeSave() {
        return new SpecificationSection(null, "spec-section-test-title");
    }

    public static SpecificationSection entityAfterSave() {
        return new SpecificationSection(1L, "spec-section-test-title");
    }

    public static SpecificationSection entityAfterFindById() {
        return new SpecificationSection(1L, "spec-section-test-title");
    }

    public static SpecificationSection entityToUpdate() {
        return new SpecificationSection(1L, "new-spec-section-test-title");
    }

}
