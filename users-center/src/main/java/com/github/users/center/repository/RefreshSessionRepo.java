package com.github.users.center.repository;

import com.github.users.center.entity.RefreshSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefreshSessionRepo extends JpaRepository<RefreshSession, Long> {
    List<RefreshSession> findAllByUserId(Long userId);
}
