package com.github.server.mocks.services.impl

import com.github.server.mocks.payload.Specification
import com.github.server.mocks.services.ISpecificationService
import com.github.server.mocks.utils.IdsGenerator
import org.springframework.stereotype.Service

@Service
class SpecificationService(private val idsGenerator: IdsGenerator): ISpecificationService {

    var mock: Specification = Specification(
            idsGenerator.getId(),
            "Phone",
            "This is phone spec."
    );

    override fun create(specification: Specification): Specification {
        return mock
    }

    override fun readById(id: Long): Specification {
        return mock
    }

    override fun update(specification: Specification) {
    }

}