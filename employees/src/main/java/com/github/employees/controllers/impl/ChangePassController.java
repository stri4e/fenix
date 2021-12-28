package com.github.employees.controllers.impl;

import com.github.employees.controllers.IChangePassController;
import com.github.employees.payload.ChangePassword;
import com.github.employees.services.IEmployeesService;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/change/passwords")
public class ChangePassController implements IChangePassController {

    private final PasswordEncoder passwordEncoder;

    private final IEmployeesService employeesService;

    @Override
    public Mono<Void> resetPassword(String ip, String userAgent, ChangePassword payload) {
        UserAgent agent = UserAgent.parseUserAgentString(userAgent);
        return this.employeesService.readByEmail(payload.getEmail())
                .map(employee -> employee.encodedPassword(this.passwordEncoder.encode(payload.getPass()))
                ).then();
    }

}
