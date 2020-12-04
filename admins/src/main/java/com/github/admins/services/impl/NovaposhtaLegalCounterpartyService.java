package com.github.admins.services.impl;

import com.github.admins.dto.NovaposhtaLegalCounterpartyDto;
import com.github.admins.services.INovaposhtaLegalCounterpartyService;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class NovaposhtaLegalCounterpartyService implements INovaposhtaLegalCounterpartyService {

    @Override
    public NovaposhtaLegalCounterpartyDto create(@Valid NovaposhtaLegalCounterpartyDto payload) {
        return null;
    }

    @Override
    public NovaposhtaLegalCounterpartyDto read() {
        return null;
    }

    @Override
    public void update(@Valid NovaposhtaLegalCounterpartyDto payload) {

    }

    @Override
    public void remove(Long id) {

    }
}
