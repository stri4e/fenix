package com.github.users.center.repository;

import com.github.users.center.entity.PassResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassResetRepo extends JpaRepository<PassResetToken, Long> {
    Optional<PassResetToken> findByToken(String token);
}
