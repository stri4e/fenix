package com.github.products.controllers.impl;

import com.github.products.controllers.ISpecSectionController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/spec/section")
@RequiredArgsConstructor
public class SpecSectionController implements ISpecSectionController {
}
