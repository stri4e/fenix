package com.github.employees.controllers.impl;

import com.github.employees.controllers.IResetPassController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/reset/passwords")
public class ResetPassController implements IResetPassController {
}
