package com.github.bitcoin.repository;

import com.github.bitcoin.entity.Address;
import com.github.bitcoin.entity.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {

    List<Address> findAllByStatus(EntityStatus status);

    @Query(value = "select a.address from Address a where a.status=:status")
    List<String> findAllAddresses(@Param(value = "status") EntityStatus status);

    Optional<Address> findByAddress(String address);

    @Modifying
    @Query(value = "update Address a set a.address=:address where a.status=:status")
    void updateStatus(
            @Param(value = "address") String address,
            @Param(value = "status") EntityStatus status
    );

}
