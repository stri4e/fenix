package com.github.employees.controllers.impl;

import com.github.employees.controllers.IEmergencyContactController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/emergency/contacts")
public class EmergencyContactController implements IEmergencyContactController {

}
