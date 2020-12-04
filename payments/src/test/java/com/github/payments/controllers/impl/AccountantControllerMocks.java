package com.github.payments.controllers.impl;

import com.github.payments.dto.AccountantDto;
import com.github.payments.entity.Accountant;

public class AccountantControllerMocks {

    public static AccountantDto request() {
        return new AccountantDto(
                null, "Vasia", "Pupkin", "Grizli"
        );
    }

    public static AccountantDto response() {
        return new AccountantDto(
                1L, "Vasia", "Pupkin", "Grizli"
        );
    }

    public static Accountant forSave() {
        return new Accountant(
                "Vasia", "Pupkin", "Grizli"
        );
    }

    public static AccountantDto requestForUpdate() {
        return new AccountantDto(
               1L, "Vasia1", "Pupkin1", "Grizli1"
        );
    }

}
