package com.github.users.center.services.impl;

import com.github.users.center.entity.EntityStatus;
import com.github.users.center.entity.RefreshSession;
import com.github.users.center.exceptions.BadRequest;
import com.github.users.center.repository.RefreshSessionRepo;
import com.github.users.center.services.IRefreshSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
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
    public List<RefreshSession> readAllByUserId(UUID userId) {
        if (Objects.isNull(userId)) {
            throw new BadRequest();
        }
        return this.rsr.findAllByUserId(userId);
    }

    @Override
    public RefreshSession readActiveByUserId(UUID userId) {
        return this.rsr.findByUserIdAndStatus(userId, EntityStatus.on);
    }

    @Override
    public void remove(Long sessionId) {
        if (Objects.nonNull(sessionId)) {
            this.rsr.deleteById(sessionId);
        }
    }

    @Override
    public void update(Long sessionId, EntityStatus status) {
        this.rsr.update(sessionId, status);
    }

}
