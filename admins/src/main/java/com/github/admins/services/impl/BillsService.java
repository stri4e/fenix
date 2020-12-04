package com.github.admins.services.impl;

import com.github.admins.dto.BillDto;
import com.github.admins.services.IBillsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillsService implements IBillsService {
    @Override
    public Optional<List<BillDto>> findByStatus(String status) {
        return Optional.empty();
    }
}
