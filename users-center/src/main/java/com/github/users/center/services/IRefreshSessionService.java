package com.github.users.center.services;

import com.github.users.center.entity.RefreshSession;

import java.util.List;

public interface IRefreshSessionService {
    RefreshSession create(RefreshSession rs);
    List<RefreshSession> readAllByUserId(Long userId);
    void remove(Long sessionId);
}
