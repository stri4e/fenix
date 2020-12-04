package com.github.payments.service.impl;

import com.github.payments.entity.Accountant;
import com.github.payments.entity.EntityStatus;
import com.github.payments.exceptions.NotFound;
import com.github.payments.repository.AccountantRepo;
import com.github.payments.service.IAccountantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountantService implements IAccountantService {

    private final AccountantRepo accountantRepo;

    @Override
    public Accountant create(Accountant accountant) {
        return this.accountantRepo.save(accountant);
    }

    @Override
    public Accountant readActive() {
        return this.accountantRepo.findByStatus(EntityStatus.on)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(Long id, String firsName, String lastName, String patronymic) {
        this.accountantRepo.update(id, firsName, lastName, patronymic);
    }

    @Override
    public void delete(Long id) {
        this.accountantRepo.updateStatus(id, EntityStatus.off);
    }
}
