package com.github.ethereum.controllers.impl;

import com.github.ethereum.dto.ContractDto;
import com.github.ethereum.entity.Contract;

import java.util.List;

public class ContractControllerMocks {

    public static ContractDto requestForUpdate() {
        return new ContractDto(
                1L,
                "FECORE1",
                "FECORE.FUND1",
                "0xd408a9520a2bA40c08F460ba9EA6C91c639bD909",
                8
        );
    }

    public static Contract contractForSave() {
        return new Contract(
                null,
                "FECORE",
                "FECORE.FUND",
                "0xd408a9520a2bA40c08F460ba9EA6C91c639bD909",
                8
        );
    }

    public static ContractDto request() {
        return new ContractDto(
                null,
                "FECORE",
                "FECORE.FUND",
                "0xd408a9520a2bA40c08F460ba9EA6C91c639bD909",
                8
        );
    }

    public static ContractDto response() {
        return new ContractDto(
                1L,
                "FECORE",
                "FECORE.FUND",
                "0xd408a9520a2bA40c08F460ba9EA6C91c639bD909",
                8
        );
    }

    public static List<Contract> CONTRACT_FOR_SAVE = List.of(
            contractOneForSave(),
            contractTwoForSave(),
            contractThreeForSave(),
            contractFourForSave(),
            contractFiveForSave()
    );

    public static Contract contractOneForSave() {
        return new Contract(
                null,
                "FECORE",
                "FECORE.FUND",
                "0xd408a9520a2bA40c08F460ba9EA6C91c639bD909",
                8
        );
    }

    public static Contract contractTwoForSave() {
        return new Contract(
                null,
                "FECORE1",
                "FECORE.FUND1",
                "0xd408a9520a2bA40c08F460ba9EA6C91c639bD909",
                8
        );
    }

    public static Contract contractThreeForSave() {
        return new Contract(
                null,
                "FECORE2",
                "FECORE.FUND2",
                "0xd408a9520a2bA40c08F460ba9EA6C91c639bD909",
                8
        );
    }

    public static Contract contractFourForSave() {
        return new Contract(
                null,
                "FECORE3",
                "FECORE.FUND3",
                "0xd408a9520a2bA40c08F460ba9EA6C91c639bD909",
                8
        );
    }

    public static Contract contractFiveForSave() {
        return new Contract(
                null,
                "FECORE4",
                "FECORE.FUND4",
                "0xd408a9520a2bA40c08F460ba9EA6C91c639bD909",
                8
        );
    }

    public static List<ContractDto> CONTRACT_FOR_EQUALS = List.of(
            contractOneForEquals(),
            contractTwoForEquals(),
            contractThreeForEquals(),
            contractFourForEquals(),
            contractFiveForEquals()
    );

    public static ContractDto contractOneForEquals() {
        return new ContractDto(
                1L,
                "FECORE",
                "FECORE.FUND",
                "0xd408a9520a2bA40c08F460ba9EA6C91c639bD909",
                8
        );
    }

    public static ContractDto contractTwoForEquals() {
        return new ContractDto(
                2L,
                "FECORE1",
                "FECORE.FUND1",
                "0xd408a9520a2bA40c08F460ba9EA6C91c639bD909",
                8
        );
    }

    public static ContractDto contractThreeForEquals() {
        return new ContractDto(
                3L,
                "FECORE2",
                "FECORE.FUND2",
                "0xd408a9520a2bA40c08F460ba9EA6C91c639bD909",
                8
        );
    }

    public static ContractDto contractFourForEquals() {
        return new ContractDto(
                4L,
                "FECORE3",
                "FECORE.FUND3",
                "0xd408a9520a2bA40c08F460ba9EA6C91c639bD909",
                8
        );
    }

    public static ContractDto contractFiveForEquals() {
        return new ContractDto(
                5L,
                "FECORE4",
                "FECORE.FUND4",
                "0xd408a9520a2bA40c08F460ba9EA6C91c639bD909",
                8
        );
    }

}
