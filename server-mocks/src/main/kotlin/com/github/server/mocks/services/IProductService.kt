package com.github.server.mocks.services

import com.github.server.mocks.payload.Product
import com.github.server.mocks.payload.ProductStatus
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

interface IProductService {

    fun create(product: Product): Product

    fun readById(id: Long): Product

    fun readByIds(ids: List<Long>): List<Product>

    fun readAll(): List<Product>

    fun update(product: Product)

    fun updateStatus(id: Long, status: ProductStatus)

}