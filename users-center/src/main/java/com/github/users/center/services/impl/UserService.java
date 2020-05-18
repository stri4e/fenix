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

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepo ur;

    @Override
    public void create(User u) {
        if (Objects.isNull(u)) {
            throw new BadRequest();
        }
        this.ur.save(u);
    }

    @Override
    public User readById(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequest();
        }
        return this.ur.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public User readByEmail(String email) {
        if (!StringUtils.hasText(email)) {
            throw new BadRequest();
        }
        return this.ur.findByEmail(email)
                .orElseThrow(NotFound::new);
    }

    @Override
    public User readByLogin(String login) {
        if (!StringUtils.hasText(login)) {
            throw new BadRequest();
        }
        return this.ur.findByLogin(login)
                .orElseThrow(NotFound::new);
    }

    @Override
    public User readByEmailOrLogin(String email, String login) {
        if (StringUtils.hasText(email) && StringUtils.hasText(login)) {
            return this.ur.findByEmailOrLogin(email, login)
                    .orElseThrow(NotFound::new);
        }
        throw new BadRequest();
    }

    @Override
    public boolean existsByEmailOrLogin(String email, String login) {
        if (StringUtils.hasText(email) && StringUtils.hasText(login)) {
            return this.ur.existsByEmailOrLogin(email, login);
        }
        return false;
    }

    @Override
    public void updatePass(String pass, Long id) {
        if (!StringUtils.hasText(pass) || Objects.isNull(id)) {
            throw new BadRequest();
        }
        this.ur.updatePass(pass, id);
    }

    @Override
    public void updateIsEnable(boolean isEnable, Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequest();
        }
        this.ur.updateIsEnable(isEnable, id);
    }

}
