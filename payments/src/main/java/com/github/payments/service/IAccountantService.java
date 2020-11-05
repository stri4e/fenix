package com.github.payments.service;

import com.github.payments.entity.Accountant;

public interface IAccountantService {

    Accountant create(Accountant accountant);

    Accountant readActive();

    void update(Long id, String firsName, String lastName, String patronymic);

    void delete(Long id);

}
