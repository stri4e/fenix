package com.github.ethereum.controllers.impl;

import com.github.ethereum.controllers.IConfigController;
import com.github.ethereum.services.IContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConfigController implements IConfigController {

    private final IContractService tokenService;

}
