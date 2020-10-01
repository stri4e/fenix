package com.github.ethereum.repository;

import com.github.ethereum.entity.Contract;
import com.github.ethereum.entity.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContractRepo extends JpaRepository<Contract, Long> {

    Optional<Contract> findByName(String name);

    Optional<Contract> findByAddress(String address);

    @Query("select c.address from Contract c")
    List<String> findAllAddress();

    @Modifying
    @Query("update Contract c set c.status=:status where c.id=:id")
    void updateStatus(
            @Param(value = "id") Long id,
            @Param(value = "status") EntityStatus status
    );

}
