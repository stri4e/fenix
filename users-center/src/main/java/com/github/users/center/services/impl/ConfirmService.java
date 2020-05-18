package com.github.users.center.services.impl;

import com.github.users.center.entity.ConfirmToken;
import com.github.users.center.exceptions.BadRequest;
import com.github.users.center.exceptions.NotFound;
import com.github.users.center.repository.ConfirmTokenRepo;
import com.github.users.center.services.IConfirmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class ConfirmService implements IConfirmService {

    private final ConfirmTokenRepo ctr;

    @Override
    public void create(ConfirmToken ct) {
        if (Objects.isNull(ct)) {
            throw new BadRequest();
        }
        this.ctr.save(ct);
    }

    @Override
    public ConfirmToken readByToken(String token) {
        if (StringUtils.hasText(token)) {
            return this.ctr.findByToken(token)
                    .orElseThrow(NotFound::new);
        }
        throw new BadRequest();
    }

}
