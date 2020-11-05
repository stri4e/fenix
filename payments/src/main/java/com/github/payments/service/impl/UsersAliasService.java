package com.github.payments.service.impl;

import com.github.payments.service.IUsersAliasService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsersAliasService implements IUsersAliasService {
    @Override
    public Optional<String> findEndingUrl(UUID userId) {
        return Optional.empty();
    }
}
