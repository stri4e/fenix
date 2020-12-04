package com.github.statistics.utils;

import com.github.statistics.dto.ClientDto;
import com.github.statistics.dto.LoginDto;
import com.github.statistics.entity.Client;
import com.github.statistics.entity.Login;

public class TransferObj {

    public static Login toLogin(LoginDto data) {
        return new Login(
                data.getId(),
                data.getToken(),
                data.getIp(),
                data.getInformation()
        );
    }

    public static LoginDto fromLogin(Login data) {
        return new LoginDto(
                data.getId(),
                data.getToken(),
                data.getIp(),
                data.getInformation()
        );
    }

    public static Client toClient(ClientDto data) {
        return new Client(
                data.getId(),
                data.getFirstName(),
                data.getLastName(),
                data.getEmail()
        );
    }

    public static ClientDto fromClient(Client data) {
        return new ClientDto(
                data.getId(),
                data.getFirstName(),
                data.getLastName(),
                data.getEmail(),
                data.getPhone()
        );
    }

}
