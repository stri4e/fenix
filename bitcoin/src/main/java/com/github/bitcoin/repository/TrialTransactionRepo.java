package com.github.bitcoin.repository;

import com.github.bitcoin.entity.EntityStatus;
import com.github.bitcoin.entity.TrialTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrialTransactionRepo extends JpaRepository<TrialTransaction, Long> {

    Optional<TrialTransaction> findByHash(String hash);

    @Modifying
    @Query(value = "update TrialTransaction  t set t.status=:status where t.hash=:hash")
    void update(String hash, EntityStatus status);

}
