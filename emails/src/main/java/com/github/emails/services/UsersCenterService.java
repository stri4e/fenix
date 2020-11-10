package com.github.emails.services;

import com.github.emails.payload.ConfirmReport;

import java.util.Optional;

public class UsersCenterService implements IUsersCenterService {

    @Override
    public Optional<ConfirmReport> confirmAccount(String token) {
        return Optional.empty();
    }

    @Override
    public Optional<ConfirmReport> resetPassword(String token) {
        return Optional.empty();
    }
}
