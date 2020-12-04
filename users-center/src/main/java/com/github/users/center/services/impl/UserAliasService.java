package com.github.users.center.services.impl;

import com.github.users.center.entity.UserAlias;
import com.github.users.center.repository.UserAliasRepo;
import com.github.users.center.services.IUserAliasService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAliasService implements IUserAliasService {

    private final UserAliasRepo userAliasRepo;

    @Override
    public void save(UserAlias userAlias) {
        this.userAliasRepo.save(userAlias);
    }

    @Override
    public String alias(UUID userId) {
        return this.userAliasRepo.findAlias(userId);
    }
}
