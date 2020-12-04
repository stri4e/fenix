package com.github.admins.controllers.impl;

import com.github.admins.dto.BillDto;
import com.github.admins.dto.WhoDto;
import com.github.admins.dto.WhomDto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class BillsControllerMocks {

    public static List<BillDto> BILLS = List.of(
            one(), two(), three(), four(), five()
    );

    public static BillDto one() {
        return new BillDto(
                1L,
                BigInteger.valueOf(60L),
                BigInteger.valueOf(54L),
                "ethereum",
                "address",
                new ArrayList<>(),
                "crypto",
                "def",
                new WhoDto(
                        1L,
                        "Vasia",
                        "Pupkin",
                        "Galicin"
                ),
                new WhomDto(
                        1L,
                        "Kolia",
                        "Zupkin",
                        "Shmiga"
                )
        );
    }

    public static BillDto two() {
        return new BillDto(
                1L,
                BigInteger.valueOf(60L),
                BigInteger.valueOf(54L),
                "ethereum",
                "address",
                new ArrayList<>(),
                "crypto",
                "def",
                new WhoDto(
                        1L,
                        "Vasia",
                        "Pupkin",
                        "Galicin"
                ),
                new WhomDto(
                        1L,
                        "Kolia",
                        "Zupkin",
                        "Shmiga"
                )
        );
    }

    public static BillDto three() {
        return new BillDto(
                1L,
                BigInteger.valueOf(60L),
                BigInteger.valueOf(54L),
                "ethereum",
                "address",
                new ArrayList<>(),
                "crypto",
                "def",
                new WhoDto(
                        1L,
                        "Vasia",
                        "Pupkin",
                        "Galicin"
                ),
                new WhomDto(
                        1L,
                        "Kolia",
                        "Zupkin",
                        "Shmiga"
                )
        );
    }

    public static BillDto four() {
        return new BillDto(
                1L,
                BigInteger.valueOf(60L),
                BigInteger.valueOf(54L),
                "ethereum",
                "address",
                new ArrayList<>(),
                "crypto",
                "def",
                new WhoDto(
                        1L,
                        "Vasia",
                        "Pupkin",
                        "Galicin"
                ),
                new WhomDto(
                        1L,
                        "Kolia",
                        "Zupkin",
                        "Shmiga"
                )
        );
    }

    public static BillDto five() {
        return new BillDto(
                1L,
                BigInteger.valueOf(60L),
                BigInteger.valueOf(54L),
                "ethereum",
                "address",
                new ArrayList<>(),
                "crypto",
                "def",
                new WhoDto(
                        1L,
                        "Vasia",
                        "Pupkin",
                        "Galicin"
                ),
                new WhomDto(
                        1L,
                        "Kolia",
                        "Zupkin",
                        "Shmiga"
                )
        );
    }

}
