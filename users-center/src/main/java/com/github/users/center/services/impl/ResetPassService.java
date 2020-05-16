package com.github.users.center.services.impl;

import com.github.users.center.entity.PassResetToken;
import com.github.users.center.exceptions.BadRequest;
import com.github.users.center.exceptions.NotFound;
import com.github.users.center.repository.PassResetRepo;
import com.github.users.center.services.IResetPassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ResetPassService implements IResetPassService {

    private final PassResetRepo prr;

    @Override
    public void create(PassResetToken prt) {
        if (Objects.isNull(prt)) {
            throw new BadRequest();
        }
        this.prr.save(prt);
    }

    @Override
    public PassResetToken readByToken(String token) {
        if (StringUtils.hasText(token)) {
            return this.prr.findByToken(token)
                    .orElseThrow(NotFound::new);
        }
        throw new BadRequest();
    }

    @Override
    public void delete(PassResetToken t) {
        if (Objects.nonNull(t)) {
            this.prr.delete(t);
        }
    }
}
