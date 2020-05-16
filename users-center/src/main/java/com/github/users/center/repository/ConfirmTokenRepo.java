package com.github.users.center.repository;

import com.github.users.center.entity.ConfirmToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmTokenRepo extends JpaRepository<ConfirmToken, Long> {
    Optional<ConfirmToken> findByToken(String token);
}
