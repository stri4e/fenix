package com.github.ethereum.services.impl;

import com.github.ethereum.entity.Account;
import com.github.ethereum.entity.EntityStatus;
import com.github.ethereum.exceptions.NotFound;
import com.github.ethereum.repository.AccountRepo;
import com.github.ethereum.services.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.github.ethereum.utils.AccountSpec.byUserId;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepo accountRepo;

    @Override
    public Account create(Account account) {
        return this.accountRepo.save(account);
    }

    @Override
    public List<Account> readByUserId(Long userId) {
        return this.accountRepo.findAll(byUserId(userId));
    }

    @Override
    public List<String> readAddressesByStatus(EntityStatus status) {
        return this.accountRepo.findAllAddresses(status);
    }

    @Override
    public List<Account> readByStatus(EntityStatus status) {
        return this.accountRepo.findByStatus(status);
    }

    @Override
    public Account readByAddress(String address) {
        return this.accountRepo.findByAddress(address)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(Account account) {
        this.accountRepo.save(account);
    }

}
