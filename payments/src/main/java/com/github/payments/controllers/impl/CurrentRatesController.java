package com.github.payments.controllers.impl;

import com.github.payments.controllers.ICurrentRatesController;
import com.github.payments.dto.CurrentRateDto;
import com.github.payments.service.ICurrentRateService;
import com.github.payments.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/rates")
public class CurrentRatesController implements ICurrentRatesController {

    private final ICurrentRateService rateService;

    @Override
    public List<CurrentRateDto> findAll() {
        return this.rateService.readAll().stream()
                .map(TransferObj::fromCurrentRate)
                .collect(Collectors.toList());
    }
}
