package com.github.master.card.repository;

import com.github.master.card.entity.Account;
import com.github.master.card.entity.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepo extends JpaRepository<Account, UUID> {

    Optional<Account> findByStatus(EntityStatus status);

}
