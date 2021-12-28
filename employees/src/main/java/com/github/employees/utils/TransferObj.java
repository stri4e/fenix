package com.github.employees.utils;

import com.github.employees.entities.*;
import com.github.employees.payload.*;

import java.util.UUID;

public class TransferObj {

    public static Employee ofNewEmployee(EmployeeDetailDto data) {
        return Employee.newEmployee(
                data.getFirstName(),
                data.getLastName(),
                data.getPatronymic(),
                data.getLogin(),
                data.getEmail()
        );
    }

    public static Account toAccount(AccountDto data) {
        return new Account(
                data.getId(),
                toProfile(data.getProfile()),
                toContact(data.getContact()),
                toEmergencyContact(data.getEmergencyContact()),
                toAddress(data.getAddress())
        );
    }

    public static Address toAddress(AddressDto data) {
        return new Address(
                data.getCountry(),
                data.getRegion(),
                data.getCity(),
                data.getStreet(),
                data.getStreetNumber(),
                data.getHouseNumber(),
                data.getFlatNumber(),
                data.getZipCode()
        );
    }

    public static Contact toContact(ContactDto data) {
        return new Contact(
                data.getPhone(),
                data.getExtraPhone()
        );
    }

    public static EmergencyContact toEmergencyContact(EmergencyContactDto data) {
        return new EmergencyContact(
                data.getFirstName(),
                data.getLastName(),
                data.getPhone()
        );
    }

    public static Profile toProfile(ProfileDto data) {
        return new Profile(
                data.getFirstName(),
                data.getLastName(),
                data.getPatronymic(),
                data.getDateOfBirth(),
                data.getSex()
        );
    }

    public static AddressDto fromAddress(Address data) {
        return new AddressDto(
                data.getCountry(),
                data.getRegion(),
                data.getCity(),
                data.getStreet(),
                data.getStreetNumber(),
                data.getHouseNumber(),
                data.getFlatNumber(),
                data.getZipCode()
        );
    }

    public static ContactDto fromContact(Contact data) {
        return new ContactDto(
                data.getPhone(),
                data.getExtraPhone()
        );
    }

    public static AccountDto fromAccount(Account data) {
        return new AccountDto(
                data.getId(),
                fromProfile(data.getProfile()),
                fromContact(data.getContact()),
                fromEmergencyContact(data.getEmergencyContact()),
                fromAddress(data.getAddress())
        );
    }

    public static ProfileDto fromProfile(Profile data) {
        return new ProfileDto(
                data.getFirstName(),
                data.getLastName(),
                data.getPatronymic(),
                data.getDateOfBirth(),
                data.getSex()
        );
    }

    public static EmergencyContactDto fromEmergencyContact(EmergencyContact data) {
        return new EmergencyContactDto(
                data.getFirstName(),
                data.getLastName(),
                data.getPhone()
        );
    }

    public static TrustDevice toTrustDevice(UUID employeeId, TrustDeviceDto data) {
        return new TrustDevice(
                employeeId,
                data.getIp(),
                data.getDeviceType(),
                data.getOsName(),
                data.getNumber()
        );
    }

    public static TrustDeviceDto fromTrustDevice(TrustDevice data) {
        return new TrustDeviceDto(
                data.getId(),
                data.getIp(),
                data.getDeviceType(),
                data.getOsName(),
                data.getNumber()
        );
    }

}
