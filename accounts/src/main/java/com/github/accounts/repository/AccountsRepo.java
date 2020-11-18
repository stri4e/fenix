package com.github.accounts.repository;

import com.github.accounts.entity.Account;
import com.github.accounts.entity.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountsRepo extends JpaRepository<Account, Long> {

    Optional<Account> findByUserId(UUID userId);

    @Modifying
    @Query(value = "update Account a set a.status=:status where a.id=:id")
    void delete(@Param(value = "id") Long id, @Param(value = "status") EntityStatus status);

}
