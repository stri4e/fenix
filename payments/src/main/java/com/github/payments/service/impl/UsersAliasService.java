package com.github.payments.service.impl;

import com.github.payments.service.IUsersAliasService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersAliasService implements IUsersAliasService {
    @Override
    public Optional<String> findEndingUrl(Long userId) {
        return Optional.empty();
    }
}
