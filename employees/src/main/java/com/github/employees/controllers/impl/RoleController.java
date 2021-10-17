package com.github.employees.controllers.impl;

import com.github.employees.controllers.IRoleController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/roles")
public class RoleController implements IRoleController {



}
