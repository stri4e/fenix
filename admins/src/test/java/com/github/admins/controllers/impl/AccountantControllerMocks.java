package com.github.admins.controllers.impl;

import com.github.admins.dto.AccountantDto;

public class AccountantControllerMocks {

    public static AccountantDto request() {
        return new AccountantDto(
            null, "Vasia", "Pupkin", "Grizli"
        );
    }

    public static AccountantDto response() {
        return new AccountantDto(
                null, "Vasia", "Pupkin", "Grizli"
        );
    }

}
