package com.github.payments.service;

import com.github.payments.entity.CurrentRate;

import java.util.List;

public interface ICurrentRateService {

    void saveAll(List<CurrentRate> rates);

}
