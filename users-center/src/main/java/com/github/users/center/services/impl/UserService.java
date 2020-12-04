package com.github.users.center.services.impl;

import com.github.users.center.entity.User;
import com.github.users.center.exceptions.BadRequest;
import com.github.users.center.exceptions.NotFound;
import com.github.users.center.repository.UserRepo;
import com.github.users.center.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepo userRepo;

    @Override
    public void create(User u) {
        if (Objects.isNull(u)) {
            throw new BadRequest();
        }
        this.userRepo.save(u);
    }

    @Override
    public User readById(UUID id) {
        if (Objects.isNull(id)) {
            throw new BadRequest();
        }
        return this.userRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public User readByEmail(String email) {
        if (!StringUtils.hasText(email)) {
            throw new BadRequest();
        }
        return this.userRepo.findByEmail(email)
                .orElseThrow(NotFound::new);
    }

    @Override
    public User readByLogin(String login) {
        if (!StringUtils.hasText(login)) {
            throw new BadRequest();
        }
        return this.userRepo.findByLogin(login)
                .orElseThrow(NotFound::new);
    }

    @Override
    public User readByEmailOrLogin(String email, String login) {
        if (StringUtils.hasText(email) && StringUtils.hasText(login)) {
            return this.userRepo.findByEmailOrLogin(email, login)
                    .orElseThrow(NotFound::new);
        }
        throw new BadRequest();
    }

    @Override
    public boolean existsByEmailOrLogin(String email, String login) {
        if (StringUtils.hasText(email) && StringUtils.hasText(login)) {
            return this.userRepo.existsByEmailOrLogin(email, login);
        }
        return false;
    }

    @Override
    public void updatePass(String pass, UUID id) {
        if (!StringUtils.hasText(pass) || Objects.isNull(id)) {
            throw new BadRequest();
        }
        this.userRepo.updatePass(pass, id);
    }

    @Override
    public void updateIsEnable(boolean isEnable, UUID id) {
        if (Objects.isNull(id)) {
            throw new BadRequest();
        }
        this.userRepo.updateIsEnable(isEnable, id);
    }

    @Override
    public void updateIsLocked(String email, boolean isLocked) {
        if (StringUtils.hasText(email)) {
            throw new BadRequest();
        }
        this.userRepo.updateIsLocked(isLocked, email);
    }

}
