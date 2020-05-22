package com.github.server.mocks.services.impl

import com.github.server.mocks.payload.Product
import com.github.server.mocks.payload.ProductStatus
import com.github.server.mocks.services.IProductService
import com.github.server.mocks.utils.IdsGenerator
import org.springframework.stereotype.Service
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Service
class ProductService(private val idsGenerator: IdsGenerator): IProductService {

    override fun create(product: Product): Product {
        TODO("Not yet implemented")
    }

    override fun readByParams(id: Long, ids: List<Long>): JvmType.Object {
        TODO("Not yet implemented")
    }

    override fun update(product: Product) {
        TODO("Not yet implemented")
    }

    override fun updateStatus(id: Long, status: ProductStatus) {
        TODO("Not yet implemented")
    }
}