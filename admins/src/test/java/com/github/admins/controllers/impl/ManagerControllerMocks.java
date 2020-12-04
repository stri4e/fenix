package com.github.admins.controllers.impl;

import com.github.admins.dto.ManagerDto;

public class ManagerControllerMocks {

    public static ManagerDto response() {
        return new ManagerDto(
            "First Name", "Last Name"
        );
    }

}
