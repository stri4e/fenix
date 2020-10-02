package com.github.ethereum.services;

import com.github.ethereum.entity.Account;
import com.github.ethereum.entity.EntityStatus;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAccountService {

    Account create(Account account);

    List<Account> readByUserId(Long id);

    List<String> readAddressesByStatus(EntityStatus status);

    Page<Account> readAllByStatus(EntityStatus status);
    Account readByUserIdAndByStatus(Long userId, EntityStatus status);

    List<Account> readByStatus(EntityStatus status);

    Account readByAddress(String address);

    void update(Account account);

}
