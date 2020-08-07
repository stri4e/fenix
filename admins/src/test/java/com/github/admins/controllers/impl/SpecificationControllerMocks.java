package com.github.admins.controllers.impl;

import com.github.admins.dto.SpecificationDto;

public class SpecificationControllerMocks {

    public static final Long ID = 1L;

    public static final String SPECIFICATION_NAME = "Phone";

    public static final String SPECIFICATION_DESCRIPTION = "This is phone spec.";

    public static SpecificationDto expSpec() {
        return new SpecificationDto(
                ID,
                SPECIFICATION_NAME,
                SPECIFICATION_DESCRIPTION
        );
    }

    public static SpecificationDto payload() {
        return new SpecificationDto(
                SPECIFICATION_NAME,
                SPECIFICATION_DESCRIPTION
        );
    }

    public static SpecificationDto payloadWithId() {
        return new SpecificationDto(
                ID,
                SPECIFICATION_NAME,
                SPECIFICATION_DESCRIPTION
        );
    }

}
