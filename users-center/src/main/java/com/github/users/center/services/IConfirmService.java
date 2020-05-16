package com.github.users.center.services;

import com.github.users.center.entity.ConfirmToken;

public interface IConfirmService {
    void create(ConfirmToken ct);
    ConfirmToken readByToken(String token);
}
