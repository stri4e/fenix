package com.github.bitcoin.controllers.impl;

import com.github.bitcoin.controllers.IFeePerKbyteController;
import com.github.bitcoin.dto.FeePerKbDto;
import com.github.bitcoin.services.IFeePerKbService;
import com.github.bitcoin.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.bitcoin.utils.TransferObj.fromFeePerKb;
import static com.github.bitcoin.utils.TransferObj.toFeePerKb;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/fee/per/kb")
public class FeePerKbyteController implements IFeePerKbyteController {

    private final IFeePerKbService feePerKbService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public FeePerKbDto save(FeePerKbDto payload) {
        return fromFeePerKb(this.feePerKbService.create(toFeePerKb(payload)));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public FeePerKbDto findActual() {
        return fromFeePerKb(this.feePerKbService.readActual());
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void update(FeePerKbDto payload) {
        this.feePerKbService.update(payload.getId(), payload.getFee());
    }
}
