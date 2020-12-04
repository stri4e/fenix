package com.github.statistics.controllers.impl;

import com.github.statistics.controllers.ILoginsController;
import com.github.statistics.dto.LoginDto;
import com.github.statistics.services.ILoginsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.github.statistics.utils.TransferObj.toLogin;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/logins")
public class LoginsController implements ILoginsController {

    private final ILoginsService loginService;

    @Override
    public void save(@Valid LoginDto payload) {
        this.loginService.create(toLogin(payload));
    }

    @Override
    public Long countByLastHour() {
        return this.loginService.countByLastHour();
    }

}
