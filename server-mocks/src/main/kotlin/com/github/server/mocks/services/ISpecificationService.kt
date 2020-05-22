package com.github.server.mocks.services

import com.github.server.mocks.payload.Specification

interface ISpecificationService {

    fun create(specification: Specification): Specification

    fun readById(id: Long): Specification

    fun update(specification: Specification)

}