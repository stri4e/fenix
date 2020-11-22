package com.github.websocket.controllers;

import com.github.websocket.dto.BillDto;
import com.github.websocket.dto.WhoDto;
import com.github.websocket.dto.WhomDto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.UUID;

public class BillsControllerMocks {

    public static final UUID STOMP_SESSION_ID = UUID.fromString("9da421d7-33e4-4319-9288-0f503bd20c3d");

    public static BillDto request() {
        return new BillDto(
                1L,
                BigInteger.valueOf(60L),
                BigInteger.valueOf(54L),
                "ethereum",
                "address",
                new ArrayList<>(),
                "crypto",
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
