package com.github.payments.utils;

import com.github.payments.service.ICryptoCurrencyMapper;
import org.springframework.stereotype.Component;

@Component
public class DefaultMapper implements ICryptoCurrencyMapper {

    @Logging
    @Override
    public void remove(String address) {
    }

}
