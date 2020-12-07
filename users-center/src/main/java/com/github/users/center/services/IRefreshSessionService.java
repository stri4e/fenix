package com.github.users.center.services;

import com.github.users.center.entity.EntityStatus;
import com.github.users.center.entity.RefreshSession;

import java.util.List;
import java.util.UUID;

public interface IRefreshSessionService {

    RefreshSession create(RefreshSession rs);

    List<RefreshSession> readAllByUserId(UUID userId);

    RefreshSession readActiveByUserId(UUID userId);

    void remove(Long sessionId);

    void update(Long sessionId, EntityStatus status);

}
