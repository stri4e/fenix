package com.github.payments.service;

import com.github.payments.entity.Rate;

import java.util.List;

public interface IRatesService {

    List<Rate> saveAll(List<Rate> rates);

}
