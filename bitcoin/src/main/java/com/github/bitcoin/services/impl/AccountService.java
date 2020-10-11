package com.github.bitcoin.services.impl;

import com.github.bitcoin.repository.AccountRepo;
import com.github.bitcoin.services.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepo accountRepo;



}
