package com.github.products.services.impl;

import com.github.products.entity.SpecSection;

public class SpecSectionServiceMocks {

    public static SpecSection entityBeforeSave() {
        return new SpecSection(null, "spec-section-test-title");
    }

    public static SpecSection entityAfterSave() {
        return new SpecSection(1L, "spec-section-test-title");
    }

    public static SpecSection entityAfterFindById() {
        return new SpecSection(1L, "spec-section-test-title");
    }

    public static SpecSection entityToUpdate() {
        return new SpecSection(1L, "new-spec-section-test-title");
    }

}
