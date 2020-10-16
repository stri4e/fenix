package com.github.bitcoin.controllers.impl;

import com.github.bitcoin.controllers.IFeePerKbyteController;
import com.github.bitcoin.dto.FeePerKbDto;
import com.github.bitcoin.services.IFeePerKbService;
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
    public FeePerKbDto save(FeePerKbDto payload) {
        return fromFeePerKb(this.feePerKbService.create(toFeePerKb(payload)));
    }

    @Override
    public FeePerKbDto findActual() {
        return fromFeePerKb(this.feePerKbService.readActual());
    }

    @Override
    public void update(FeePerKbDto payload) {
        this.feePerKbService.update(toFeePerKb(payload));
    }
}
