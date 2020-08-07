package com.github.server.mocks.controllers

import com.github.server.mocks.payload.Specification
import com.github.server.mocks.services.ISpecificationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/specification")
class SpecificationController(private val specificationService: ISpecificationService) {

    @PostMapping(
            "/edit"
    )
    fun create(@RequestBody payload: Specification): Specification {
        return this.specificationService.create(payload)
    }

    @GetMapping(
            "/fetch/{id}"
    )
    fun readById(@PathVariable id: Long): Specification {
        return this.specificationService.readById(id)
    }

    @PutMapping(
            "/edit"
    )
    fun update(@RequestBody specification: Specification) {
        this.specificationService.update(specification)
    }

}