package com.github.admins.controllers.impl;

import com.github.admins.dto.PaymentTypesDto;

import java.util.List;

public class PaymentTypeControllerMocks {

    public static List<PaymentTypesDto> PAYMENTS = List.of(
            one(), two(), three(), four(), five()
    );

    public static PaymentTypesDto request() {
        return new PaymentTypesDto(
                null, "payment alias"
        );
    }

    public static PaymentTypesDto one() {
        return new PaymentTypesDto(
                1L, "payment alias"
        );
    }

    public static PaymentTypesDto two() {
        return new PaymentTypesDto(
                1L, "payment alias"
        );
    }

    public static PaymentTypesDto three() {
        return new PaymentTypesDto(
                1L, "payment alias"
        );
    }

    public static PaymentTypesDto four() {
        return new PaymentTypesDto(
                1L, "payment alias"
        );
    }

    public static PaymentTypesDto five() {
        return new PaymentTypesDto(
                1L, "payment alias"
        );
    }

}
