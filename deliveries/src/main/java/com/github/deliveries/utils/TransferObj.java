package com.github.deliveries.utils;

import com.github.deliveries.dto.*;
import com.github.deliveries.entity.*;

import java.util.Objects;
import java.util.UUID;

public class TransferObj {

    public static Company toCompany(CompanyDto data) {
        return new Company(
                data.getId(),
                data.getName()
        );
    }

    public static CompanyDto fromCompany(Company data) {
        return new CompanyDto(
                data.getId(),
                data.getName()
        );
    }

    public static NovaposhtaSettingsDto fromNovaposhtaSettings(NovaposhtaSettings data) {
        return new NovaposhtaSettingsDto(
                data.getId(),
                data.getBaseUrl(),
                data.getApiKey(),
                data.getHeaders()
        );
    }

    public static NovaposhtaSettings toNovaposhtaSettings(NovaposhtaSettingsDto data) {
        return new NovaposhtaSettings(
                data.getId(),
                data.getBaseUrl(),
                data.getApiKey(),
                data.getHeaders()
        );
    }

    public static MeestSettings toMeestSettings(MeestSettingsDto data) {
        return new MeestSettings(
                data.getBaseUrl(),
                data.getHeader()
        );
    }

    public static MeestSettingsDto fromMeestSettings(MeestSettings data) {
        MeestSession tokens = data.getSession();
        return new MeestSettingsDto(
                data.getId(),
                data.getBaseUrl(),
                Objects.isNull(tokens) ? null : tokens.getToken(),
                data.getHeader()
        );
    }

    public static MeestUser toMeestUser(MeestUserDto data) {
        return new MeestUser(
                data.getLogin(),
                data.getPass()
        );
    }

    public static CustomerLastDelivery toDelivery(CustomerLastDeliveryDto data, UUID userId) {
        return new CustomerLastDelivery(
                data.getId(),
                data.getType(),
                data.getCompanyName(),
                userId
        );
    }

    public static CustomerLastDeliveryDto fromDelivery(CustomerLastDelivery data) {
        Address address = data.getAddress();
        return new CustomerLastDeliveryDto(
                data.getId(),
                data.getType(),
                data.getCompanyName(),
                Objects.nonNull(address) ? fromAddress(address) : new AddressDto()
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
