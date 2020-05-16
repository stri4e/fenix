package com.github.users.center.services;

import com.github.users.center.entity.PassResetToken;

public interface IResetPassService {
    void create(PassResetToken prt);
    PassResetToken readByToken(String token);
    void delete(PassResetToken t);
}
