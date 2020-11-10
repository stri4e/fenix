package com.github.ethereum.services;

import com.github.ethereum.entity.Account;
import com.github.ethereum.entity.EntityStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IAccountService {

    Account create(Account account);

    List<Account> readByUserId(UUID id);

    List<String> readAddressesByStatus(EntityStatus status);

    Page<Account> readAllByStatus(Pageable pageable, EntityStatus status);

    Account readByUserIdAndByStatus(UUID userId, EntityStatus status);

    Account readByAddress(String address);

    void update(Account account);

    void updateStatus(String address, EntityStatus status);

    int countAccountByUserId(UUID userId);

}
