package com.github.emails.services;

import com.github.emails.payload.RenderTemplate;

import java.util.Optional;

public class UsersCenterService implements IUsersCenterService {

    @Override
    public Optional<RenderTemplate> confirmAccount(String token) {
        return Optional.empty();
    }

    @Override
    public Optional<RenderTemplate> resetPassword(String token) {
        return Optional.empty();
    }
}
