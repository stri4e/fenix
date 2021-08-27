package com.github.products.controllers.impl;

import com.github.products.controllers.ISpecSectionController;
import com.github.products.dto.SpecSectionDto;
import com.github.products.dto.SpecificationDto;
import com.github.products.services.ISpecSectionService;
import com.github.products.services.ISpecificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

import static com.github.products.utils.TransferObj.fromSpecSection;
import static com.github.products.utils.TransferObj.toSpecSection;

@RestController
@RequestMapping(path = "/spec/section")
@RequiredArgsConstructor
public class SpecSectionController implements ISpecSectionController {

    private final ISpecSectionService specSectionService;

    private final ISpecificationService specificationService;

    @Override
    public SpecSectionDto save(SpecSectionDto payload) {
        return fromSpecSection(
                this.specSectionService.create(
                        toSpecSection(payload)
                                .addSpecifications(
                                        this.specificationService.readAllById(
                                                payload.getSpecification().stream()
                                                        .map(SpecificationDto::getId)
                                                        .collect(Collectors.toList())
                                        )
                                )
                )
        );
    }

    @Override
    public void updateTitle(Long specSectionId, String title) {
        this.specSectionService.updateTitle(specSectionId, title);
    }

    @Override
    public void deleteById(Long specSectionId) {
        this.specSectionService.remove(specSectionId);
    }

}
