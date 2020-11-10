package com.github.bitcoin.controllers.impl;

import com.github.bitcoin.dto.AccountDto;
import com.github.bitcoin.dto.AddressDto;
import com.github.bitcoin.entity.Account;
import com.github.bitcoin.entity.Address;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountControllerMocks {

    public static final UUID userId = UUID.fromString("9da421d7-33e4-4319-9288-0f503bd20c3d");

    public static final String ADDRESS_FOR_EQUALS = "mmv44pgWexgw7odiTd5JzCCwf71VfnfD8U";

    public static AccountDto response() {
        return new AccountDto(
                1L,
                BigInteger.ZERO,
                ADDRESSES_FOR_EQUALS,
                new ArrayList<>()
        );
    }

    public static Account account() {
        return new Account(
                userId,
                "bag brick speed surface identify silk scare perfect fatal turn snake skull",
                "00a5177473ed9d4ac1cc4af6952cd047a11bca61bd3b30177e39d1f8dd2b1a136a",
                "03bbf82576e2ed06fd0dd7cbc79c30251f058fa6427922481de85c448eae770e2a",
                "3636f29f3f7e43db8af5b0b5058b1b6eab6efa10ce019f38af9d756db4275fa9",
                1605012002940L
        );
    }

    public static List<Address> addresses(Account account) {
        List<Address> addresses = new ArrayList<>();
        addresses.add(firstKeyPairZero(account));
        addresses.add(firstKeyPairOne(account));
        return addresses;
    }

    public static Address addressForSave(Account account) {
        return new Address(
                1, "mmv44pgWexgw7odiTd5JzCCwf71VfnfD8U", account
        );
    }

    public static Address firstKeyPairZero(Account account) {
        return new Address(
                0, "miTvGKxxyC9GFzZbZrfwmtckQXvF2Y3Pjh", account
        );
    }

    public static Address firstKeyPairOne(Account account) {
        return new Address(
                1, "mmv44pgWexgw7odiTd5JzCCwf71VfnfD8U", account
        );
    }

    public static List<AddressDto> ADDRESSES_FOR_EQUALS = List.of(
            addressZeroForEquals(),
            addressOneForEquals()
    );

    public static AddressDto addressZeroForEquals() {
        return new AddressDto(
                1L, 0, "miTvGKxxyC9GFzZbZrfwmtckQXvF2Y3Pjh", BigInteger.ZERO
        );
    }

    public static AddressDto addressOneForEquals() {
        return new AddressDto(
               2L, 1, "mmv44pgWexgw7odiTd5JzCCwf71VfnfD8U", BigInteger.ZERO
        );
    }

}
