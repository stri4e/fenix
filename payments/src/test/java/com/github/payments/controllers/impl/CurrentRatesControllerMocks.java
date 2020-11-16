package com.github.payments.controllers.impl;

import com.github.payments.dto.CurrentRateDto;
import com.github.payments.dto.RateDto;
import com.github.payments.entity.CurrentRate;
import com.github.payments.entity.Rate;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class CurrentRatesControllerMocks {

    public static CurrentRateDto currentRate() {
        return new CurrentRateDto(
                1L,
                "BTC",
                RATES_FOR_EQUALS
        );
    }

    public static CurrentRate currentRateForSave(List<Rate> rates) {
        long hours = BigInteger.TEN.longValue();
        LocalDateTime now = LocalDateTime.now();
        return new CurrentRate(
            "BTC", rates, now.minus(hours, ChronoUnit.HOURS)
        );
    }

    public static List<Rate> RATES = List.of(
            rateOne(),
            rateTwo(),
            rateThree(),
            rateFour()
    );

    public static List<RateDto> RATES_FOR_EQUALS = List.of(
            rateOneForEquals(),
            rateTwoForEquals(),
            rateThreeForEquals(),
            rateFourForEquals()
    );

    public static Rate rateOne() {
        return new Rate(
                "Mon Nov 09 13:49:35 EET 2020",
                "USD",
                3258.8875417798037784035133948
        );
    }

    public static Rate rateTwo() {
        return new Rate(
                "Mon Nov 09 13:49:35 EET 2020",
                "EUR",
                2782.5255080599273092901331567
        );
    }

    public static Rate rateThree() {
        return new Rate(
                "Mon Nov 09 13:49:35 EET 2020",
                "CNY",
                21756.295595926054627342411501
        );
    }

    public static Rate rateFour() {
        return new Rate(
                "Mon Nov 09 13:49:35 EET 2020",
                "GBP",
                2509.6024203799580199765804823
        );
    }

    public static RateDto rateOneForEquals() {
        return new RateDto(
                1L,
                "Mon Nov 09 13:49:35 EET 2020",
                "USD",
                3258.8875417798037784035133948
        );
    }

    public static RateDto rateTwoForEquals() {
        return new RateDto(
                2L,
                "Mon Nov 09 13:49:35 EET 2020",
                "EUR",
                2782.5255080599273092901331567
        );
    }

    public static RateDto rateThreeForEquals() {
        return new RateDto(
                3L,
                "Mon Nov 09 13:49:35 EET 2020",
                "CNY",
                21756.295595926054627342411501
        );
    }

    public static RateDto rateFourForEquals() {
        return new RateDto(
                4L,
                "Mon Nov 09 13:49:35 EET 2020",
                "GBP",
                2509.6024203799580199765804823
        );
    }

}
