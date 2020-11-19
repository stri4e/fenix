package com.github.orders.service.impl;

import com.github.orders.dto.BillDto;
import com.github.orders.service.IBillService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillService implements IBillService {

    @Override
    public Optional<BillDto> findById(Long id) {
        return Optional.empty();
    }

}
