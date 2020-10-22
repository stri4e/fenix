package com.github.payments.service.impl;

import com.github.payments.service.IUsersNotifyService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersNotifyService implements IUsersNotifyService {
    @Override
    public Optional<String> findUrlEnding(Long userId) {
        return Optional.empty();
    }
}
