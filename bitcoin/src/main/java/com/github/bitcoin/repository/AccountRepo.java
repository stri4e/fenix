package com.github.bitcoin.repository;

import com.github.bitcoin.entity.Account;
import com.github.bitcoin.entity.EntityStatus;
import com.github.bitcoin.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

    Optional<Account> findByUserId(Long userId);

    Page<Account> findAllByStatus(EntityStatus status);

}
