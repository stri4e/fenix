package com.github.users.center.services.impl;

import com.github.users.center.entity.RefreshSession;
import com.github.users.center.exceptions.BadRequest;
import com.github.users.center.repository.RefreshSessionRepo;
import com.github.users.center.services.IRefreshSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RefreshSessionService implements IRefreshSessionService {

    private final RefreshSessionRepo rsr;

    @Override
    public RefreshSession create(RefreshSession rs) {
        if (Objects.isNull(rs)) {
            throw new BadRequest();
        }
        return this.rsr.save(rs);
    }

    @Override
    public List<RefreshSession> readAllByUserId(Long userId) {
        if (Objects.isNull(userId)) {
            throw new BadRequest();
        }
        return this.rsr.findAllByUserId(userId);
    }

    @Override
    public void remove(Long sessionId) {
        if (Objects.nonNull(sessionId)) {
            this.rsr.deleteById(sessionId);
        }
    }

}
