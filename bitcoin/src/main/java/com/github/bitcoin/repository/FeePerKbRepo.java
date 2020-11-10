package com.github.bitcoin.repository;

import com.github.bitcoin.entity.EntityStatus;
import com.github.bitcoin.entity.FeePerKb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface FeePerKbRepo extends JpaRepository<FeePerKb, Long> {

    Optional<FeePerKb> findByStatus(EntityStatus status);

    @Modifying
    @Query(value = "update FeePerKb fp set fp.fee=:fee where fp.id=:id")
    void update(@Param(value = "id") Long id, @Param(value = "fee") BigInteger fee);

}
