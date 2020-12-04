package com.github.ethereum.repository;

import com.github.ethereum.entity.EntityStatus;
import com.github.ethereum.entity.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface FeeRepo extends JpaRepository<Fee, Long> {

    Fee findByStatus(EntityStatus status);

    @Modifying
    @Query(value = "update Fee f set f.fee=:fee, f.gasPrice=:gasPrice where f.status=:status")
    void update(
            @Param(value = "fee") BigInteger fee,
            @Param(value = "gasPrice") BigInteger gasPrice,
            @Param(value = "status") EntityStatus status
    );

}
