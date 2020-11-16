package com.github.ethereum.controllers.impl;

import com.github.ethereum.controllers.IFeeController;
import com.github.ethereum.dto.FeeDto;
import com.github.ethereum.entity.EntityStatus;
import com.github.ethereum.services.IFeeService;
import com.github.ethereum.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.ethereum.utils.TransferObj.fromFee;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/fee")
public class FeeController implements IFeeController {

    private final IFeeService feeService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public FeeDto findFee() {
        return fromFee(this.feeService.readByStatus(EntityStatus.on));
    }

}
