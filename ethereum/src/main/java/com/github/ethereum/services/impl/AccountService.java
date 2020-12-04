package com.github.ethereum.services.impl;

import com.github.ethereum.entity.Account;
import com.github.ethereum.entity.EntityStatus;
import com.github.ethereum.exceptions.NotFound;
import com.github.ethereum.repository.AccountRepo;
import com.github.ethereum.services.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

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
    public List<Account> readByUserId(UUID userId) {
        return this.accountRepo.findAll(byUserId(userId));
    }

    @Override
    public List<String> readAddressesByStatus(EntityStatus status) {
        return this.accountRepo.findAllAddresses(status);
    }

    @Override
    public Account readByUserIdAndByStatus(UUID userId, EntityStatus status) {
        return this.accountRepo.findFirstByUserIdAndStatus(userId, status)
                .orElseThrow(NotFound::new);
    }

    @Override
    public Page<Account> readAllByStatus(Pageable pageable, EntityStatus status) {
        return this.accountRepo.findAllByStatus(pageable, status);
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

    @Override
    public void updateStatus(String address, EntityStatus status) {
        this.accountRepo.updateStatus(address, status);
    }

    @Override
    public int countAccountByUserId(UUID userId) {
        return this.accountRepo.countAccountByUserId(userId);
    }

}
