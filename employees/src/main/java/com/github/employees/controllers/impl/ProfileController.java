package com.github.employees.controllers.impl;

import com.github.employees.controllers.IProfileController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/profiles")
public class ProfileController implements IProfileController {
}
