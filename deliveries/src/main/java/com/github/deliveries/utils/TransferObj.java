package com.github.deliveries.utils;

import com.github.deliveries.dto.CompanyDto;
import com.github.deliveries.dto.MeestSettingsDto;
import com.github.deliveries.dto.MeestUserDto;
import com.github.deliveries.dto.NovaposhtaSettingsDto;
import com.github.deliveries.entity.Company;
import com.github.deliveries.entity.MeestSettings;
import com.github.deliveries.entity.MeestUser;
import com.github.deliveries.entity.NovaposhtaSettings;

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
        return new MeestSettingsDto(
                data.getId(),
                data.getBaseUrl(),
                data.getTokens().getToken(),
                data.getHeader()
        );
    }

    public static MeestUser toMeestUser(MeestUserDto data) {
        return new MeestUser(
                data.getLogin(),
                data.getPass()
        );
    }

}
