package com.github.payments.controllers.impl;

import com.github.payments.dto.PaymentTypesDto;
import com.github.payments.entity.PaymentTypes;

import java.util.List;

public class PaymentTypeControllerMocks {

    public static PaymentTypesDto request() {
        return new PaymentTypesDto(
                1L, "BTC"
        );
    }

    public static PaymentTypesDto paymentTypesForEquals() {
        return new PaymentTypesDto(
                1L, "BTC"
        );
    }

    public static PaymentTypesDto paymentTypesForUpdate() {
        return new PaymentTypesDto(
                1L, "LTC"
        );
    }

    public static List<PaymentTypesDto> PAYMENT_TYPE_FOR_EQUALS = List.of(
            paymentTypesOneForEquals(),
            paymentTypesTwoForEquals(),
            paymentTypesThreeForEquals(),
            paymentTypesFourForEquals(),
            paymentTypesFiveForEquals()
    );

    public static PaymentTypesDto paymentTypesOneForEquals() {
        return new PaymentTypesDto(
                1L, "LTC"
        );
    }

    public static PaymentTypesDto paymentTypesTwoForEquals() {
        return new PaymentTypesDto(
                2L, "ETH"
        );
    }

    public static PaymentTypesDto paymentTypesThreeForEquals() {
        return new PaymentTypesDto(
                3L, "ETC"
        );
    }

    public static PaymentTypesDto paymentTypesFourForEquals() {
        return new PaymentTypesDto(
                4L, "QTUM"
        );
    }

    public static PaymentTypesDto paymentTypesFiveForEquals() {
        return new PaymentTypesDto(
                5L, "ICE"
        );
    }

    public static PaymentTypes paymentTypesForSave() {
        return new PaymentTypes(
                null, "BTC"
        );
    }

    public static List<PaymentTypes> PAYMENT_TYPE_FOR_SAVE = List.of(
            paymentTypesOneForSave(),
            paymentTypesTwoForSave(),
            paymentTypesThreeForSave(),
            paymentTypesFourForSave(),
            paymentTypesFiveForSave()
    );

    public static PaymentTypes paymentTypesOneForSave() {
        return new PaymentTypes(
                null, "LTC"
        );
    }

    public static PaymentTypes paymentTypesTwoForSave() {
        return new PaymentTypes(
                null, "ETH"
        );
    }

    public static PaymentTypes paymentTypesThreeForSave() {
        return new PaymentTypes(
                null, "ETC"
        );
    }

    public static PaymentTypes paymentTypesFourForSave() {
        return new PaymentTypes(
                null, "QTUM"
        );
    }

    public static PaymentTypes paymentTypesFiveForSave() {
        return new PaymentTypes(
                null, "ICE"
        );
    }

}
