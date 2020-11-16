package com.github.ethereum.controllers.impl;

import com.github.ethereum.dto.AccountDto;
import com.github.ethereum.entity.Account;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AccountControllerMocks {

    public static final String EXP_ADDRESS = "0x65E5BC985b8399B338C3C55ff1e3c048586d50ca";

    public static final UUID userId = UUID.fromString("9da421d7-33e4-4319-9288-0f503bd20c3d");

    public static List<AccountDto> ACCOUNTS_FOR_EQUALS = List.of(
            accountOneForEquals(),
            accountTwoForEquals(),
            accountThreeForEquals(),
            accountFourForEquals(),
            accountFiveForEquals()
    );

    public static AccountDto accountForEquals() {
        return new AccountDto(
                "0x93ba538D15642b703aBA9aDAB81d25D589e116d1",
                BigInteger.TEN,
                contractBalance()
        );
    }

    public static AccountDto accountOneForEquals() {
        return new AccountDto(
                "0x93ba538D15642b703aBA9aDAB81d25D589e116d1",
                BigInteger.TEN,
                contractBalance()
        );
    }

    public static AccountDto accountTwoForEquals() {
        return new AccountDto(
                "0x428515066618cfa126a667b20a334c0704f3f886",
                BigInteger.TEN,
                contractBalance()
        );
    }

    public static AccountDto accountThreeForEquals() {
        return new AccountDto(
                "0x4fa8E964B1F255E2833bA75B5588955F26D7e63F",
                BigInteger.TEN,
                contractBalance()
        );
    }

    public static AccountDto accountFourForEquals() {
        return new AccountDto(
                "0x8EEFC0843bc9f9d019c58cD7f4444840E708315E",
                BigInteger.TEN,
                contractBalance()
        );
    }

    public static AccountDto accountFiveForEquals() {
        return new AccountDto(
                "0x65E5BC985b8399B338C3C55ff1e3c048586d50ca",
                BigInteger.TEN,
                contractBalance()
        );
    }

    public static List<Account> ACCOUNTS_FOR_SAVE = List.of(
            accountOneForSave(),
            accountTwoForSave(),
            accountThreeForSave(),
            accountFourForSave(),
            accountFiveForSave()
    );

    public static Account accountForSave() {
        return new Account(
                userId,
                new BigInteger("111"),
                new BigInteger("111"),
                "0x93ba538D15642b703aBA9aDAB81d25D589e116d1",
                BigInteger.TEN,
                contractBalance()
        );
    }

    public static Account accountOneForSave() {
        return new Account(
                userId,
                new BigInteger("111"),
                new BigInteger("111"),
                "0x93ba538D15642b703aBA9aDAB81d25D589e116d1",
                BigInteger.TEN,
                contractBalance()
        );
    }

    public static Account accountTwoForSave() {
        return new Account(
                userId,
                new BigInteger("222"),
                new BigInteger("222"),
                "0x428515066618cfa126a667b20a334c0704f3f886",
                BigInteger.TEN,
                contractBalance()
        );
    }

    public static Account accountThreeForSave() {
        return new Account(
                userId,
                new BigInteger("333"),
                new BigInteger("333"),
                "0x4fa8E964B1F255E2833bA75B5588955F26D7e63F",
                BigInteger.TEN,
                contractBalance()
        );
    }

    public static Account accountFourForSave() {
        return new Account(
                userId,
                new BigInteger("444"),
                new BigInteger("444"),
                "0x8EEFC0843bc9f9d019c58cD7f4444840E708315E",
                BigInteger.TEN,
                contractBalance()
        );
    }

    public static Account accountFiveForSave() {
        return new Account(
                userId,
                new BigInteger("555"),
                new BigInteger("555"),
                "0x65E5BC985b8399B338C3C55ff1e3c048586d50ca",
                BigInteger.TEN,
                contractBalance()
        );
    }

    public static Map<String, BigInteger> contractBalance() {
        Map<String, BigInteger> result = new HashMap<>();
        result.put("BNB", BigInteger.TEN);
        result.put("COC", BigInteger.TEN);
        result.put("DOC", BigInteger.TEN);
        return result;
    }

}
