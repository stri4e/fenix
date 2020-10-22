package com.github.payments.service;

import com.github.payments.entity.Accountant;

public interface IAccountantService {

    Accountant create(Accountant accountant);

    Accountant readActive();

    void update(Accountant accountant);

    void delete(Long id);

}
