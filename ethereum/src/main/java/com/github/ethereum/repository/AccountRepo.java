package com.github.ethereum.repository;

import com.github.ethereum.entity.Account;
import com.github.ethereum.entity.EntityStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long>,
        PagingAndSortingRepository<Account, Long>, JpaSpecificationExecutor<Account> {

    Optional<Account> findByAddress(String address);

    @Query(value = "select a.address from Account a where a.status=:status")
    List<String> findAllAddresses(@Param(value = "status") EntityStatus status);

    Page<Account> findAllByStatus(EntityStatus status);

}
