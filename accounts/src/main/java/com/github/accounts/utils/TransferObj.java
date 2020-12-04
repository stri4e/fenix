package com.github.accounts.utils;

import com.github.accounts.dto.*;
import com.github.accounts.entity.Account;
import com.github.accounts.entity.Address;
import com.github.accounts.entity.Contact;
import com.github.accounts.entity.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransferObj {

    public static Account toAccount(UUID userId, AccountDto data) {
        return new Account(
                userId,
                toProfile(data.getProfile()),
                toContact(data.getContact()),
                toAddress(data.getAddress())
        );
    }

    public static AccountDto fromAccount(Account data, List<ProductDto> views) {
        return new AccountDto(
                data.getId(),
                fromProfile(data.getProfile()),
                fromContact(data.getContact()),
                fromAddress(data.getAddress()),
                views
        );
    }

    public static AccountDto fromAccount(Account data) {
        return new AccountDto(
                data.getId(),
                fromProfile(data.getProfile()),
                fromContact(data.getContact()),
                fromAddress(data.getAddress()),
                new ArrayList<>()
        );
    }

    public static Contact toContact(ContactDto data) {
        return new Contact(
                data.getId(),
                data.getPhone(),
                data.getEmail()

        );
    }

    public static ContactDto fromContact(Contact data) {
        return new ContactDto(
                data.getId(),
                data.getPhone(),
                data.getEmail()
        );
    }

    public static Profile toProfile(ProfileDto data) {
        return new Profile(
                data.getId(),
                data.getFirstName(),
                data.getLastName(),
                data.getPatronymic(),
                data.getDateOfBirth(),
                data.getSex()
        );
    }

    public static ProfileDto fromProfile(Profile data) {
        return new ProfileDto(
                data.getId(),
                data.getFirstName(),
                data.getLastName(),
                data.getPatronymic(),
                data.getDateOfBirth(),
                data.getSex()
        );
    }

    public static Address toAddress(AddressDto data) {
        return new Address(
                data.getId(),
                data.getCountry(),
                data.getRegion(),
                data.getCity(),
                data.getStreet(),
                data.getStreetNumber(),
                data.getFlatNumber(),
                data.getZipCode()
        );
    }

    public static AddressDto fromAddress(Address data) {
        return new AddressDto(
                data.getId(),
                data.getCountry(),
                data.getRegion(),
                data.getCity(),
                data.getStreet(),
                data.getStreetNumber(),
                data.getFlatNumber(),
                data.getZipCode()
        );
    }

}
